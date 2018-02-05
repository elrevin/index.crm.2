package me.elrevin.indexcrm.common.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.EmployeModel;
import me.elrevin.indexcrm.mvp.model.PersonModel;

public class EmployeesRecyclerAdapter extends RecyclerView.Adapter<EmployeesRecyclerAdapter.ViewHolder> {
    private ArrayList<EmployeModel> list;

    protected OnCallToEmployeHandler callHandler;

    public EmployeesRecyclerAdapter(ArrayList<EmployeModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employe_item, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EmployeModel item = list.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnCallHandler(OnCallToEmployeHandler handler) {
        callHandler = handler;
    }

    public void onCall(EmployeModel emplloye) {
        if (callHandler != null) {
            callHandler.call(emplloye);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected EmployeesRecyclerAdapter adapter;

        @BindView(R.id.cvEmployeItem)
        CardView cv;

        @BindView(R.id.tvEmployeName)
        TextView tvEmployeName;

        @BindView(R.id.tvEmployeStatus)
        TextView tvEmployeStatus;

        @BindView(R.id.btnCall)
        ImageButton btnCall;

        EmployeModel item;

        public ViewHolder(View itemView, EmployeesRecyclerAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            ButterKnife.bind(this, itemView);
        }

        public void setItem(EmployeModel item) {
            this.item = item;
            tvEmployeName.setText(item.getName());
            tvEmployeStatus.setText(item.getStatus());
        }

        @OnClick(R.id.btnCall)
        public void onBtnCallClick() {
            adapter.onCall(item);
        }
    }
}
