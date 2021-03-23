package com.example.insuranceagent.business.clients.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.clients.data.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapterRV extends RecyclerView.Adapter<ClientAdapterRV.ClientViewHolder> {

    private List<Client> clientList = new ArrayList<>();
    private Context context;

    public ClientAdapterRV(Context context) {
        this.context = context;
    }

    public void setList(List<Client> clientList) {
        this.clientList = clientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_client, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.bind(clientList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {
        private ImageView imItemLifeInsCompany;
        private TextView tvItemClinetName;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            imItemLifeInsCompany = itemView.findViewById(R.id.imItemLifeInsCompany);
            tvItemClinetName = itemView.findViewById(R.id.tvItemClinetName);
        }

        public void bind(String title) {
            tvItemClinetName.setText(title);
            Glide.with(context)
                    .load("https://media.licdn.com/dms/image/C4E0BAQGvfZGd31Hw_Q/company-logo_200_200/0?e=2159024400&v=beta&t=zXUgvlTYqfO4cCIr5kggBOcypfhK4i4pbMtzq6dbeP8")
                    .into(imItemLifeInsCompany);
        }
    }
}
