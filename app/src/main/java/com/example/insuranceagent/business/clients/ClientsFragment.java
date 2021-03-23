package com.example.insuranceagent.business.clients;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insuranceagent.App;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.clients.adapter.ClientAdapterRV;
import com.example.insuranceagent.business.clients.data.database.room.ClientDao;
import com.example.insuranceagent.business.clients.data.database.room.ClientDatabase;
import com.example.insuranceagent.business.clients.data.model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private List<Client> clients = new ArrayList<>();
    private ClientDao clientDao;

    private RecyclerView rv;
    private ClientAdapterRV adapterRV;


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

        rv = view.findViewById(R.id.rvClient);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterRV = new ClientAdapterRV(getContext());
        //new AddClient().execute();
        new GetClients().execute();

    }

    class AddClient extends AsyncTask<Void, Void, Void> {
        private Client client = new Client("Yevhenii Yeromenko",
                "100361863",
                "100361864",
                "+380956180868",
                "м.Помічна, вул. Будівельників 7, кв.30");

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.addClient(client);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetClients().execute();
        }
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
            for (int i = 0; i < list.size(); i++) {
                Log.wtf("______ROOM DATABASE______", list.get(i).getName());
                Log.wtf("______ROOM DATABASE______", list.get(i).getPolicyFirstNumber());
                Log.wtf("______ROOM DATABASE______", list.get(i).getAddress());
            }

            rv.setAdapter(adapterRV);
            adapterRV.setList(list);


        }
    }
}