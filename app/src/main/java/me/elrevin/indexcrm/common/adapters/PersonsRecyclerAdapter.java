package me.elrevin.indexcrm.common.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.PersonModel;
import me.elrevin.indexcrm.ui.activity.BaseActivity;

public class PersonsRecyclerAdapter extends RecyclerView.Adapter<PersonsRecyclerAdapter.ViewHolder> {

    private ArrayList<PersonModel> list;
    private Context context;

    public PersonsRecyclerAdapter(ArrayList<PersonModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_on_client_item, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonModel item = list.get(position);

        holder.setPerson(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView post;
        RelativeLayout callContainer;
        ImageButton call;
        PersonModel person;
        Context context;

        ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            cv = (CardView) itemView.findViewById(R.id.cvPersonItem);
            name = (TextView) itemView.findViewById(R.id.tvPersonName);
            post = (TextView) itemView.findViewById(R.id.tvPersonPost);
            callContainer = (RelativeLayout) itemView.findViewById(R.id.rlPersonPhone);
            call = (ImageButton) itemView.findViewById(R.id.btnCall);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity) context).phoneCall(person.getPhone());
                }
            });
        }

        public void setPerson(PersonModel person) {
            this.person = person;
            name.setText(person.getName());
            if (person.getPosition() != null && !person.getPosition().isEmpty()) {
                post.setVisibility(View.VISIBLE);
                post.setText(person.getPosition());
            } else {
                post.setVisibility(View.GONE);
            }

            if (
                    person.getPhone() != null
                    && !person.getPhone().isEmpty()
                ) {
                callContainer.setVisibility(View.VISIBLE);
            } else {
                callContainer.setVisibility(View.GONE);
            }
        }

        public Context getContext() {
            return context;
        }
    }

}
