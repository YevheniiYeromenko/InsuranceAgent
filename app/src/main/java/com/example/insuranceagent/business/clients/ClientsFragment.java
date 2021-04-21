package com.example.insuranceagent.business.clients;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.insuranceagent.App;
import com.example.insuranceagent.ItemClickSupport;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.adapter.ClientAdapterRV;
import com.example.insuranceagent.business.data.database.room.ClientDao;
import com.example.insuranceagent.business.data.database.room.InsuranceDatabase;
import com.example.insuranceagent.business.data.model.Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClientsFragment extends Fragment {

    private List<Client> clientList = new ArrayList<>();
    private ClientDao clientDao;

    private RecyclerView rv;
    private ClientAdapterRV adapterRV;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            Log.e("TAG", "onCreate: " + getArguments().getString("BUNDLE"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        InsuranceDatabase database = App.getInstance().getInsuranceDatabase();
        clientDao = database.clientDao();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setTitle("Клієнти");
        toolbar.getMenu().setGroupVisible(0, true);

        //Navigation.findNavController(view).popBackStack();
        //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        FloatingActionButton fabAddClient = view.findViewById(R.id.fabAddClient);
        fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_clientsFragment_to_addClientFragment);
            }
        });

        rv = view.findViewById(R.id.rvClient);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterRV = new ClientAdapterRV(getContext());
        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("CLIENT_INFO", clientList.get(position));
                Navigation.findNavController(v).navigate(R.id.action_clientsFragment_to_clinetInfoFragment, bundle);
            }
        });
        ItemClickSupport.addTo(rv).setOnItemLongClickListener((recyclerView, position, v) -> {
            //new DeleteClient().execute(clientList.get(position));

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Видалити контакт?")
                    .setPositiveButton("Так", (dialog, which) -> {
//                        new DeleteClient().execute(clientList.get(position));
                        clientDao.deleteClientRx(clientList.get(position))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(getContext(), "Видалено", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                        Log.e("onError", "onError: DELETE");
                                    }
                                });

                    })
                    .setNegativeButton("Ні", (dialog, which) -> {
                    })
                    .setNeutralButton("Редагувати", (dialog, which) -> Toast.makeText(getContext(), "Редагування", Toast.LENGTH_SHORT).show())
                    .show();

            return false;
        });

//        new GetClients().execute();
        Disposable subscribe = clientDao.getAllClientsRx()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Client>>() {
                    @Override
                    public void accept(List<Client> list) throws Exception {
                        clientList = list;
                        rv.setAdapter(adapterRV);
                        adapterRV.setList(list);
                    }
                });



    }


    class GetClients extends AsyncTask<Void, Void, List<Client>> {
        @Override
        protected List<Client> doInBackground(Void... voids) {
            List<Client> clients = clientDao.getAllClients();
            return clients;
        }

        @Override
        protected void onPostExecute(List<Client> list) {
            super.onPostExecute(list);
            clientList = list;
            for (int i = 0; i < list.size(); i++) {
//                Log.wtf("______ROOM DATABASE______", list.get(i).getName());
//                Log.wtf("______ROOM DATABASE______", list.get(i).getPolicyFirstNumber());
//                Log.wtf("______ROOM DATABASE______", list.get(i).getAddress());
            }

            rv.setAdapter(adapterRV);
            adapterRV.setList(list);


        }
    }

    class DeleteClient extends AsyncTask<Client, Void, Void> {

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.deleteClient(clients[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetClients().execute();
        }
    }


}