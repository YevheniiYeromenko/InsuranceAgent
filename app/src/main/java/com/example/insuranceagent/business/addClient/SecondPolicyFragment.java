package com.example.insuranceagent.business.addClient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.insuranceagent.R;
import com.example.insuranceagent.business.clients.data.model.PolicySecond;


public class SecondPolicyFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            Log.e("TAG", "onCreate: " + getArguments().getString("TEST_STRING"));
        }
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





        Button bCreateClient = view.findViewById(R.id.bCreateClient);
        bCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPolicyInfo(view);

                Navigation.findNavController(v).popBackStack(R.id.clientsFragment, false);
            }
        });




    }

    private void readPolicyInfo(View view) {
        PolicySecond policySecond = new PolicySecond();
        policySecond.price = ((EditText) view.findViewById(R.id.etSecondPolicyPrice)).getText().toString();
        policySecond.prgADob = ((EditText) view.findViewById(R.id.etAddADob)).getText().toString();
        policySecond.prgADTraffic = ((EditText) view.findViewById(R.id.etAddADTraffic)).getText().toString();
        policySecond.prgPI = ((EditText) view.findViewById(R.id.etAddPI)).getText().toString();
        policySecond.prgPITraffic = ((EditText) view.findViewById(R.id.etAddPITraffic)).getText().toString();
        policySecond.prgBBB = ((EditText) view.findViewById(R.id.etAddBBB)).getText().toString();
        policySecond.prgBI = ((EditText) view.findViewById(R.id.etAddBI)).getText().toString();
        policySecond.as = ((CheckBox) view.findViewById(R.id.checkBoxAs)).isChecked();
        policySecond.prgH = ((EditText) view.findViewById(R.id.etAddH)).getText().toString();
        policySecond.prgS = ((EditText) view.findViewById(R.id.etAddS)).getText().toString();
        policySecond.prgC = ((EditText) view.findViewById(R.id.etAddC)).getText().toString();
        policySecond.prgFCdiagnosis = ((EditText) view.findViewById(R.id.etAddFCdiagnosis)).getText().toString();
        policySecond.prgFCmonth = ((EditText) view.findViewById(R.id.etAddFCmonth)).getText().toString();
        policySecond.prgFCday = ((EditText) view.findViewById(R.id.etAddFCday)).getText().toString();
        policySecond.prgCFBdeath = ((EditText) view.findViewById(R.id.etAddCFBdeath)).getText().toString();
        policySecond.prgCFBhospital = ((EditText) view.findViewById(R.id.etCFBhospital)).getText().toString();
        policySecond.prgCFBreanimation = ((EditText) view.findViewById(R.id.etCFBreanimation)).getText().toString();
        policySecond.prgCIdiagnosis = ((EditText) view.findViewById(R.id.etCFBreanimation)).getText().toString();

        RadioGroup radio_group_CFB_1_10_65 = view.findViewById(R.id.radio_group_CFB__1_10_65);
        RadioGroup radio_group_CI_1_7_32 = view.findViewById(R.id.radio_group_CI_1_7_32);
        RadioGroup radio_group_CI_1_10_65 = view.findViewById(R.id.radio_group_CI_1_10_65);

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

        Log.e("TAG", "onClick: " + policySecond.toString());
    }
}