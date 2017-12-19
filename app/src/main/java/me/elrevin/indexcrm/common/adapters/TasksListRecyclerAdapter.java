package me.elrevin.indexcrm.common.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.TaskModel;


public class TasksListRecyclerAdapter extends RecyclerView.Adapter<TasksListRecyclerAdapter.ViewHolder> {
    private ArrayList<TaskModel> list;
    private Context ctx;
    private OnRecyclerViewItemClickHandler<TaskModel> onItemClickHandler;

    public TasksListRecyclerAdapter(Context ctx) {
        list = new ArrayList<>();
        this.ctx = ctx;
    }

    public void setOnItemClickHandler(OnRecyclerViewItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_on_tasks_screen, parent, false);
        return new ViewHolder(v, ctx);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskModel item = list.get(position);
        holder.setItem(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickHandler != null) {
                    onItemClickHandler.onClick(item, position);
                }
            }
        });
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

    public void addAll(List<TaskModel> items) {
        list.addAll(items);
    }

    public void addAll(List<TaskModel> items, boolean notify) {
        list.addAll(items);
        if (notify) {
            notifyDataSetChanged();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPlaner)
        TextView tvPlaner;

        @BindView(R.id.tvEndDate)
        TextView tvEndDate;

        @BindView(R.id.tvClientName)
        TextView tvClientName;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.cvTaskItem)
        CardView cvTaskItem;

        private TaskModel item;

        private Context ctx;

        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.ctx = ctx;
        }

        public void setItem(TaskModel item) {
            this.item = item;

            tvPlaner.setText(item.getPlaner());
            tvEndDate.setText(item.getPlanedEndDateF());
            tvClientName.setText(item.getClient()+":");
            tvTitle.setText(item.getTitle());

            if (item.getTimeStatus().equals("tomorrow")) {
                tvPlaner.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
                tvEndDate.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
                tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
                tvTitle.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
            }

            if (item.getTimeStatus().equals("fuckup")) {
                tvPlaner.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
                tvEndDate.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
                tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
                tvTitle.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
            }

            if (item.getTimeStatus().equals("today")) {
                tvPlaner.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
                tvEndDate.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
                tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
                tvTitle.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
            }
        }
    }
}
