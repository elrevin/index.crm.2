package me.elrevin.indexcrm.common.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.ClientModel;

public class ClientsRecyclerAdapter extends RecyclerView.Adapter<ClientsRecyclerAdapter.ViewHolder> {
    private ArrayList<ClientModel> list;

    public ClientsRecyclerAdapter() {
        this.list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item_on_clients_screen, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClientModel item = list.get(position);

        holder.name.setText(item.getName());

        if (item.getServices() == null || item.getServices().isEmpty()) {
            holder.services.setVisibility(View.GONE);
        } else {
            ArrayList<String> services = new ArrayList<>();

            for (String si : item.getServices()) {
                switch (si) {
                    case "DEV":
                        services.add("Р");
                        break;
                    case "SUP":
                        services.add("С");
                        break;
                    case "SEO_POSITIONS":
                        services.add("ПП");
                        break;
                    case "SEO_TRAFFIC":
                        services.add("ПТ");
                        break;
                    case "SMM":
                        services.add("SMM");
                        break;
                    case "START":
                        services.add("IS");
                        break;
                    case "CONTEXT_MANAGEMENT":
                        services.add("КВ");
                        break;
                    case "CONTEXT_BUDGET":
                        services.add("КБ");
                        break;
                    default:
                        services.add("ХЗ");
                        break;
                }
            }

            holder.services.setText(TextUtils.join(", ", services));
            holder.services.setVisibility(View.VISIBLE);
        }

        if (item.getLastContactDate() == null || item.getLastContactDate().isEmpty()) {
            holder.lastContactDate.setVisibility(View.GONE);
        } else {
            holder.lastContactDate.setText(item.getLastContactDate());
            holder.lastContactDate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearList() {
        list.clear();
    }

    public void clearList(boolean notify) {
        list.clear();
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void addAll(List<ClientModel> items) {
        list.addAll(items);
    }

    public void addAll(List<ClientModel> items, boolean notify) {
        list.addAll(items);
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView services;
        TextView lastContactDate;
        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvClientItem);
            name = (TextView)itemView.findViewById(R.id.tvClientName);
            services = (TextView)itemView.findViewById(R.id.tvClientServices);
            lastContactDate = (TextView)itemView.findViewById(R.id.tvClientLastContactDate);
        }
    }
}
