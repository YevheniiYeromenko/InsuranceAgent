package com.example.insuranceagent.business.addClient;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.insuranceagent.App;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.clients.ClientsFragment;
import com.example.insuranceagent.business.clients.data.database.room.ClientDao;
import com.example.insuranceagent.business.clients.data.database.room.ClientDatabase;
import com.example.insuranceagent.business.clients.data.model.Client;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddClientFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextInputEditText etAddClientName;
    private TextInputEditText etAddClientTel;
    private TextInputEditText etAddClientAddress;
    private TextInputEditText etAddpolicyFirstNumber;
    private TextInputEditText etAddpolicySecondNumber;
    private TextInputEditText etAddClientDuration;
    private TextInputEditText etAddClientStartDate;

    private TextInputLayout tilAddClientName;
    private TextInputLayout tilAddClientTel;
    private TextInputLayout tilAddClientAddress;
    private TextInputLayout tilAddPolicyFirstNumber;
    private TextInputLayout tilAddPolicySecondNumber;
    private TextInputLayout tilAddClientDuration;
    private TextInputLayout tilAddClientStartDate;


    private boolean expandableFlag = false;
    private Button bNext;

    private ClientDao clientDao;
    private NavController navController;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ClientDatabase database = App.getInstance().getClientDatabase();
        clientDao = database.clientDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
//        ((Toolbar)(getActivity().findViewById(R.id.main_toolbar)))
//                .getMenu()
//                .getItem(0)
//                .setVisible(false);

        etAddClientName = view.findViewById(R.id.etAddClientName);
        etAddpolicyFirstNumber = view.findViewById(R.id.etAddpolicyFirstNumber);
        etAddpolicySecondNumber = view.findViewById(R.id.etAddpolicySecondNumber);
        etAddClientTel = view.findViewById(R.id.etAddClientTel);
        etAddClientAddress = view.findViewById(R.id.etAddClientAddress);
        etAddClientDuration = view.findViewById(R.id.etAddClientDuration);
        etAddClientStartDate = view.findViewById(R.id.etAddClientStartDate);

        tilAddClientName = view.findViewById(R.id.tilAddClientName);
        tilAddPolicyFirstNumber = view.findViewById(R.id.tilAddPolicyFirstNumber);
        tilAddPolicySecondNumber = view.findViewById(R.id.tilAddPolicySecondNumber);
        tilAddClientTel = view.findViewById(R.id.tilAddClientTel);
        tilAddClientAddress = view.findViewById(R.id.tilAddClientAddress);
        tilAddClientDuration = view.findViewById(R.id.tilAddClientDuration);
        tilAddClientStartDate = view.findViewById(R.id.tilAddClientStartDate);


        bNext = view.findViewById(R.id.bNext);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("TEST_STRING", "Test string");

                navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_addClientFragment_to_secondPolicyFragment, bundle);
                //navController.popBackStack();

/*                if (etAddpolicyFirstNumber.getText().length() < 9)
                    tilAddPolicyFirstNumber.setError("Короткий номер");
                else {
                    tilAddPolicyFirstNumber.setErrorEnabled(false);
                    Client client = new Client(name, tel, address, polycyFirst, polycySecond, duration, startDate);
                    navController = Navigation.findNavController(v);
//                    new AddClient(client).execute();
                }*/


            }
        });
    }

    class AddClient extends AsyncTask<Void, Void, Void> {
        private Client client;

        public AddClient(Client client) {
            this.client = client;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.addClient(client);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getContext(), "Додано", Toast.LENGTH_SHORT).show();
            navController.popBackStack();
        }
    }
}