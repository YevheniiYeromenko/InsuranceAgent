package com.example.insuranceagent.business.addClient;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.insuranceagent.App;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.data.database.room.ClientDao;
import com.example.insuranceagent.business.data.database.room.InsuranceDatabase;
import com.example.insuranceagent.business.data.model.Client;
import com.example.insuranceagent.business.data.model.PolicySecond;


public class SecondPolicyFragment extends Fragment {

    private Client client = new Client();

    private EditText etSecondPolicyPrice;
    private EditText etAddADob;
    private EditText etAddADTraffic;
    private EditText etAddPI;
    private EditText etAddPITraffic;
    private EditText etAddBBB;
    private EditText etAddBI;
    private EditText etAddH;
    private EditText etAddS;
    private EditText etAddC;
    private EditText etAddFCdiagnosis;
    private EditText etAddFCmonth;
    private EditText etAddFCday;
    private EditText etAddCFBdeath;
    private EditText etCFBhospital;
    private EditText etCFBreanimation;
    private EditText etCIdiagnosis;
    private RadioGroup radio_group_CFB_1_10_65;
    private RadioGroup radio_group_CI_1_7_32;
    private RadioGroup radio_group_CI_1_10_65;
    private CheckBox checkBoxAs;

    private ClientDao clientDao;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            client = getArguments().getParcelable("CLIENT");
            Log.e("TAG", "onCreate: " + client.name);
        }

        InsuranceDatabase database = App.getInstance().getInsuranceDatabase();
        clientDao = database.clientDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_policy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAllView(view);

        Button bCreateClient = view.findViewById(R.id.bCreateClient);
        bCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPolicyInfo();
                //Navigation.findNavController(v).popBackStack(R.id.clientsFragment, false);
                Bundle bundle = new Bundle();
                bundle.putParcelable("POLICY", client);
                navController = Navigation.findNavController(v);

                new AddClient(client).execute();
            }
        });


    }

    private void initAllView(View view) {

        etSecondPolicyPrice = view.findViewById(R.id.etSecondPolicyPrice);
        etAddADob = view.findViewById(R.id.etAddADob);
        etAddADTraffic = view.findViewById(R.id.etAddADTraffic);
        etAddPI = view.findViewById(R.id.etAddPI);
        etAddPITraffic = view.findViewById(R.id.etAddPITraffic);
        etAddBBB = view.findViewById(R.id.etAddBBB);
        etAddBI = view.findViewById(R.id.etAddBI);
        checkBoxAs = view.findViewById(R.id.checkBoxAs);
        etAddH = view.findViewById(R.id.etAddH);
        etAddS = view.findViewById(R.id.etAddS);
        etAddC = view.findViewById(R.id.etAddC);
        etAddFCdiagnosis = view.findViewById(R.id.etAddFCdiagnosis);
        etAddFCmonth = view.findViewById(R.id.etAddFCmonth);
        etAddFCday = view.findViewById(R.id.etAddFCday);
        etAddCFBdeath = view.findViewById(R.id.etAddCFBdeath);
        etCFBhospital = view.findViewById(R.id.etCFBhospital);
        etCFBreanimation = view.findViewById(R.id.etCFBreanimation);
        etCIdiagnosis = view.findViewById(R.id.etCIdiagnosis);

        radio_group_CFB_1_10_65 = view.findViewById(R.id.radio_group_CFB__1_10_65);
        radio_group_CFB_1_10_65.check(R.id.rbCFBduration1);
        radio_group_CI_1_7_32 = view.findViewById(R.id.radio_group_CI_1_7_32);
        radio_group_CI_1_7_32.check(R.id.rbCII1);
        radio_group_CI_1_10_65 = view.findViewById(R.id.radio_group_CI_1_10_65);
        radio_group_CI_1_10_65.check(R.id.rbCIduration1);
    }

    private void readPolicyInfo() {
        PolicySecond policySecond = new PolicySecond();

        policySecond.price = etSecondPolicyPrice.getText().toString();
        policySecond.prgADob = etAddADob.getText().toString();
        policySecond.prgADTraffic = etAddADTraffic.getText().toString();
        policySecond.prgPI = etAddPI.getText().toString();
        policySecond.prgPITraffic = etAddPITraffic.getText().toString();
        policySecond.prgBBB = etAddBBB.getText().toString();
        policySecond.prgBI = etAddBI.getText().toString();
        policySecond.prgH = etAddH.getText().toString();
        policySecond.prgS = etAddS.getText().toString();
        policySecond.prgC = etAddC.getText().toString();
        policySecond.prgFCdiagnosis = etAddFCdiagnosis.getText().toString();
        policySecond.prgFCmonth = etAddFCmonth.getText().toString();
        policySecond.prgFCday = etAddFCday.getText().toString();
        policySecond.prgCFBdeath = etAddCFBdeath.getText().toString();
        policySecond.prgCFBhospital = etCFBhospital.getText().toString();
        policySecond.prgCFBreanimation = etCFBreanimation.getText().toString();
        policySecond.prgCIdiagnosis = etCIdiagnosis.getText().toString();

        policySecond.as = checkBoxAs.isChecked();

        switch (radio_group_CFB_1_10_65.getCheckedRadioButtonId()) {
            case R.id.rbCFBduration1:
                policySecond.prgCFBDuration = "1";
                break;
            case R.id.rbCFBduration10:
                policySecond.prgCFBDuration = "10";
                break;
            case R.id.rbCFBduration65:
                policySecond.prgCFBDuration = "65";
                break;
        }
        switch (radio_group_CI_1_10_65.getCheckedRadioButtonId()) {
            case R.id.rbCIduration1:
                policySecond.prgCIDuration = "1";
                break;
            case R.id.rbCIduration10:
                policySecond.prgCIDuration = "10";
                break;
            case R.id.rbCIduration65:
                policySecond.prgCIDuration = "65";
                break;
        }
        switch (radio_group_CI_1_7_32.getCheckedRadioButtonId()) {
            case R.id.rbCII1:
                policySecond.prgCI_1_7_32 = "1";
                break;
            case R.id.rbCI7:
                policySecond.prgCI_1_7_32 = "7";
                break;
            case R.id.rbCI32:
                policySecond.prgCI_1_7_32 = "32";
                break;
        }

        client.policySecond = policySecond;

        Log.e("TAG", "onClick: " + policySecond.toString());
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
            navController.navigate(R.id.action_secondPolicyFragment_to_clientsFragment);
        }
    }
}