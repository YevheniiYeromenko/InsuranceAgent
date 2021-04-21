package com.example.insuranceagent.business.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insuranceagent.R;
import com.example.insuranceagent.business.data.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientInfoAdapterRV extends RecyclerView.Adapter<ClientInfoAdapterRV.ClientInfoHolder> {

    private final Client client;
    private final List<String> list = new ArrayList<>();
    private Resources resources;
    private Context context;

    public ClientInfoAdapterRV(Client client) {
        this.client = client;


        list.add(client.telNumber);
        list.add(client.address);
        list.add(client.duration);
        list.add(client.startDate);

        list.add(client.policySecond.price);
        list.add(client.policySecond.prgADob);
        list.add(client.policySecond.prgADTraffic);
        list.add(client.policySecond.prgPI);
        list.add(client.policySecond.prgPITraffic);
        list.add(client.policySecond.prgBBB);
        list.add(client.policySecond.prgBI);
        list.add(client.policySecond.prgH);
        list.add(client.policySecond.prgS);
        list.add(client.policySecond.prgC);
        list.add(client.policySecond.prgFCdiagnosis);
        list.add(client.policySecond.prgFCmonth);
        list.add(client.policySecond.prgFCday);
        list.add(client.policySecond.prgCFBdeath);
        list.add(client.policySecond.prgCFBhospital);
        list.add(client.policySecond.prgCFBreanimation);
        list.add(client.policySecond.prgCIdiagnosis);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.removeIf(s-> s.isEmpty());
        }


        /*Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            if (s.isEmpty())
                iterator.remove();
        }*/
    }

    void setList() {
        notifyDataSetChanged();
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
    }


    @NonNull
    @Override
    public ClientInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_client_info, parent, false);
        resources = parent.getResources();
        context = parent.getContext();
        return new ClientInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientInfoHolder holder, int position) {
//        holder.tvRvItemTextInfo.setText(list.get(position));

        bind(holder, position);

    }

    private void bind(ClientInfoHolder holder, int position) {
        holder.viewRvItemWideLine.setVisibility(View.GONE);
        holder.tvRvItemTitle.setVisibility(View.GONE);
        holder.viewRvItemLine.setVisibility(View.VISIBLE);



        if (list.get(position).equals(client.telNumber)) {
            holder.tvRvItemTextInfo.setText(client.telNumber);
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_tel_number);
            holder.tvRvItemTitle.setVisibility(View.VISIBLE);
            holder.tvRvItemTitle.setText("Персональні дані");
            holder.itemView.setOnClickListener(v -> dialPhoneNumber(client.telNumber));
        }
        if (list.get(position).equals(client.address)) {
            holder.tvRvItemTextInfo.setText(client.address);
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_address);
        }
        if (list.get(position).equals(client.duration)){
            holder.tvRvItemTextInfo.setText(client.duration);
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_duration);
        }
        if (list.get(position).equals(client.startDate)){
            holder.tvRvItemTextInfo.setText(client.startDate);
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_pocicy_start_date);
            holder.viewRvItemWideLine.setVisibility(View.VISIBLE);
            holder.viewRvItemLine.setVisibility(View.GONE);
        }
        if (list.get(position).equals(client.policySecond.price)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.price + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_second_price);
            holder.tvRvItemTitle.setVisibility(View.VISIBLE);
            holder.tvRvItemTitle.setText("№" + client.policySecondNumber);
        }
        if (list.get(position).equals(client.policySecond.prgADob)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgADob + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_ADob);
        }
        if (list.get(position).equals(client.policySecond.prgADTraffic)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgADTraffic + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_ADTraffic);
        }
        if (list.get(position).equals(client.policySecond.prgPI)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgPI + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_PI);
        }
        if (list.get(position).equals(client.policySecond.prgPITraffic)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgPITraffic + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_PITraffic);
        }
        if (list.get(position).equals(client.policySecond.prgBBB)){
            holder.tvRvItemTextInfo.setText(client.policySecond.prgBBB + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_BBB);
        }
        if (list.get(position).equals(client.policySecond.prgBI)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgBI + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_BI);
        }
        if (list.get(position).equals(client.policySecond.prgH)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgH + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_H);
            holder.tvRvItemTitle.setVisibility(View.VISIBLE);
            holder.tvRvItemTitle.setText(R.string.til_client_policy_HSC);
            holder.tvRvItemTitle.setTextColor(resources.getColor(R.color.green));
        }
        if (list.get(position).equals(client.policySecond.prgS)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgS + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_S);
        }
        if (list.get(position).equals(client.policySecond.prgC)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgC + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_C);
        }
        if (list.get(position).equals(client.policySecond.prgFCdiagnosis)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgFCdiagnosis + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_FC_diagnosis);
            holder.tvRvItemTitle.setVisibility(View.VISIBLE);
            holder.tvRvItemTitle.setText(R.string.til_client_policy_CFB);
            holder.tvRvItemTitle.setTextColor(resources.getColor(R.color.green));
        }
        if (list.get(position).equals(client.policySecond.prgFCmonth)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgFCmonth + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_FC_month);
        }
        if (list.get(position).equals(client.policySecond.prgFCday)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgFCday + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_FC_day);
        }
        if (list.get(position).equals(client.policySecond.prgCFBdeath)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgCFBdeath + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_CFB_death);
        }
        if (list.get(position).equals(client.policySecond.prgCFBhospital)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgCFBhospital + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_CFB_hospital);
        }
        if (list.get(position).equals(client.policySecond.prgCFBreanimation)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgCFBreanimation + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_CFB_reanimation);
        }
        if (list.get(position).equals(client.policySecond.prgCIdiagnosis)) {
            holder.tvRvItemTextInfo.setText(client.policySecond.prgCIdiagnosis + " грн");
            holder.tvRvItemTextInfoPrompt.setText(R.string.til_client_policy_CI_diagnosis);
            holder.tvRvItemTitle.setVisibility(View.VISIBLE);
            holder.tvRvItemTitle.setText(R.string.til_client_policy_CI);
            holder.tvRvItemTitle.setTextColor(resources.getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ClientInfoHolder extends RecyclerView.ViewHolder {
        private final TextView tvRvItemTitle;
        private final View viewRvItemLine;
        private final TextView tvRvItemTextInfo;
        private final TextView tvRvItemTextInfoPrompt;
        private final View viewRvItemWideLine;

        public ClientInfoHolder(@NonNull View itemView) {
            super(itemView);

            tvRvItemTitle = itemView.findViewById(R.id.tvRvItemTitle);
            viewRvItemLine = itemView.findViewById(R.id.viewRvItemLine);
            tvRvItemTextInfo = itemView.findViewById(R.id.tvRvItemTextInfo);
            viewRvItemWideLine = itemView.findViewById(R.id.viewRvItemWideLine);
            tvRvItemTextInfoPrompt = itemView.findViewById(R.id.tvRvItemTextInfoPrompt);
        }

    }
}
