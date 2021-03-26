package com.example.insuranceagent.business.clients;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.insuranceagent.business.clients.adapter.ClientAdapterRV;
import com.example.insuranceagent.business.clients.data.database.room.ClientDao;
import com.example.insuranceagent.business.clients.data.database.room.ClientDatabase;
import com.example.insuranceagent.business.clients.data.model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TAG";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth auth;
    private List<Client> clientList = new ArrayList<>();
    private ClientDao clientDao;

    private RecyclerView rv;
    private ClientAdapterRV adapterRV;
    private FloatingActionButton fabAddClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        ClientDatabase database = App.getInstance().getClientDatabase();
        clientDao = database.clientDao();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        //Navigation.findNavController(view).popBackStack();
        //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        fabAddClient = view.findViewById(R.id.fabAddClient);
        fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_clientsFragment_to_addClientFragment);
            }
        });

        rv = view.findViewById(R.id.rvClient);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterRV = new ClientAdapterRV(getContext());
        ItemClickSupport.addTo(rv).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                //new DeleteClient().execute(clientList.get(position));

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Видалити контакт?")
                        .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new DeleteClient().execute(clientList.get(position));
                                Toast.makeText(getContext(), "Видалено", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNeutralButton("Редагувати", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Редагування", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                return false;
            }
        });

        //new AddClient().execute();
        new GetClients().execute();

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
                Log.wtf("______ROOM DATABASE______", list.get(i).getName());
                Log.wtf("______ROOM DATABASE______", list.get(i).getPolicyFirstNumber());
                Log.wtf("______ROOM DATABASE______", list.get(i).getAddress());
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