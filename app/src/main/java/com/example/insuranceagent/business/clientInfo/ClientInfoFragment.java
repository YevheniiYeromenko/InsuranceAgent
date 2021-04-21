package com.example.insuranceagent.business.clientInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insuranceagent.R;
import com.example.insuranceagent.business.adapter.ClientInfoAdapterRV;
import com.example.insuranceagent.business.data.model.Client;

public class ClientInfoFragment extends Fragment {

    private Client client;
    private RecyclerView rv;
    private ClientInfoAdapterRV adapterRV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            client = getArguments().getParcelable("CLIENT_INFO");
            Log.e("TAG", "onCreate: " + client.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setTitle(client.name);
        toolbar.getMenu().getItem(0).setVisible(false);
        rv = view.findViewById(R.id.rvClientInfo);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterRV = new ClientInfoAdapterRV(client);
        rv.setAdapter(adapterRV);
    }
}