package com.example.insuranceagent.business.addClient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.insuranceagent.R;
import com.example.insuranceagent.business.data.database.room.ClientDao;
import com.example.insuranceagent.business.data.model.Client;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddClientFragment extends Fragment {

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

    private CheckBox cbAddFirstPolicy;
    private CheckBox cbAddSecondPolicy;

    private Button bNext;

    private ClientDao clientDao;
    private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setTitle("Додати клієнта");
        toolbar.getMenu().getItem(0).setVisible(false);

        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
//        ((Toolbar)(getActivity().findViewById(R.id.main_toolbar)))
//                .getMenu()
//                .getItem(0)
//                .setVisible(false);

        initAllView(view);


        bNext = view.findViewById(R.id.bNext);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Client client = new Client();
                setClient(client);

                Bundle bundle = new Bundle();
                bundle.putParcelable("CLIENT", client);

                goToNextFragment(bundle, view);

            }
        });
    }

    private void initAllView(View view) {
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

        cbAddFirstPolicy = view.findViewById(R.id.cbAddFirstPolicy);
        cbAddSecondPolicy = view.findViewById(R.id.cbAddSecondPolicy);

        etAddpolicyFirstNumber.setEnabled(cbAddFirstPolicy.isChecked());
        etAddpolicySecondNumber.setEnabled(cbAddFirstPolicy.isChecked());

        cbAddFirstPolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etAddpolicyFirstNumber.setEnabled(cbAddFirstPolicy.isChecked());
                if (!cbAddFirstPolicy.isChecked())
                    etAddpolicyFirstNumber.setText("");
            }
        });
        cbAddSecondPolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etAddpolicySecondNumber.setEnabled(cbAddSecondPolicy.isChecked());
                if (!cbAddSecondPolicy.isChecked())
                    etAddpolicySecondNumber.setText("");
            }
        });
    }

    private void goToNextFragment(Bundle bundle, View view) {
        if (!(tilAddClientName.isErrorEnabled() ||
                tilAddClientTel.isErrorEnabled() ||
                tilAddClientAddress.isErrorEnabled() ||
                tilAddClientDuration.isErrorEnabled() ||
                tilAddClientStartDate.isErrorEnabled() ||
                tilAddPolicyFirstNumber.isErrorEnabled() ||
                tilAddPolicySecondNumber.isErrorEnabled())) {
            Log.e("", "onClick: " + "NOT SET ERROR");
            navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_addClientFragment_to_secondPolicyFragment, bundle);
        }
    }

    private void setClient(Client client) {

        tilAddClientName.setErrorEnabled(false);
        tilAddClientTel.setErrorEnabled(false);
        tilAddClientAddress.setErrorEnabled(false);
        tilAddClientDuration.setErrorEnabled(false);
        tilAddClientStartDate.setErrorEnabled(false);
        tilAddPolicyFirstNumber.setErrorEnabled(false);
        tilAddPolicySecondNumber.setErrorEnabled(false);

        if (etAddClientName.getText().length() == 0) {
            tilAddClientName.setError("Заповніть поле");
            tilAddClientName.setErrorEnabled(true);
        } else {
            client.name = etAddClientName.getText().toString();
        }
        if (etAddClientTel.getText().length() == 0) {
            tilAddClientTel.setError("Заповніть поле");
            tilAddClientTel.setErrorEnabled(true);
        } else {
            client.telNumber = etAddClientTel.getText().toString();
        }
        if (etAddClientAddress.getText().length() == 0) {
            tilAddClientAddress.setError("Заповніть поле");
            tilAddClientAddress.setErrorEnabled(true);
        } else {
            client.address = etAddClientAddress.getText().toString();
        }
        if (etAddClientDuration.getText().length() == 0) {
            tilAddClientDuration.setError("Заповніть поле");
            tilAddClientDuration.setErrorEnabled(true);
        } else {
            client.duration = etAddClientDuration.getText().toString();
        }
        if (etAddClientStartDate.getText().length() == 0) {
            tilAddClientStartDate.setError("Заповніть поле");
            tilAddClientStartDate.setErrorEnabled(true);
        } else {
            client.startDate = etAddClientStartDate.getText().toString();
        }
        if (cbAddFirstPolicy.isChecked() && (etAddpolicyFirstNumber.getText().length() < 9)) {
            tilAddPolicyFirstNumber.setError("Короткий номер");
            tilAddPolicyFirstNumber.setErrorEnabled(true);
        } else {
            client.policyFirstNumber = etAddpolicyFirstNumber.getText().toString();
        }
        if (cbAddSecondPolicy.isChecked() && (etAddpolicySecondNumber.getText().length() < 9)) {
            tilAddPolicySecondNumber.setError("Короткий номер");
            tilAddPolicySecondNumber.setErrorEnabled(true);
        } else {
            client.policySecondNumber = etAddpolicySecondNumber.getText().toString();
        }
    }

}