package me.elrevin.indexcrm.common.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.ClientModel;

public class ClientsListAdapter extends CustomBaseAdapter<ClientModel> {
    public ClientsListAdapter(ArrayList<ClientModel> objects, Context ctx) {
        super(objects, ctx);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.client_item_on_main_screen, parent, false);
        }

        ClientModel c = (ClientModel) getItem(position);

        TextView tvClientName = (TextView) view.findViewById(R.id.tvClientName);
        TextView tvLastContactDate = (TextView) view.findViewById(R.id.tvLastContactDate);
        TextView tvContactsCount = (TextView) view.findViewById(R.id.tvContactsCount);

        tvClientName.setText(c.getName());
        tvLastContactDate.setText(c.getLastContactDate());
        tvContactsCount.setText(c.getContactsCount());

        return view;
    }
}
