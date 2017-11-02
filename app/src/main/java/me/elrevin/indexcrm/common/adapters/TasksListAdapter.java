package me.elrevin.indexcrm.common.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.TaskModel;

public class TasksListAdapter extends CustomBaseAdapter<TaskModel> {
    public TasksListAdapter(ArrayList<TaskModel> objects, Context ctxr) {
        super(objects, ctxr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.task_item_on_main_screen, parent, false);
        }

        TaskModel t = (TaskModel) getItem(position);
        TextView tvClientName = (TextView) view.findViewById(R.id.tvClientName);
        TextView tvTaskTitle = (TextView) view.findViewById(R.id.tvTaskTitle);
        tvClientName.setText(t.getClient());
        tvTaskTitle.setText(t.getTitle());

        if (t.getTimeStatus().equals("tomorrow")) {
            tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
            tvTaskTitle.setTextColor(ctx.getResources().getColor(R.color.colorTextGray));
        }

        if (t.getTimeStatus().equals("fuckup")) {
            tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
            tvTaskTitle.setTextColor(ctx.getResources().getColor(R.color.colorAlert));
        }

        if (t.getTimeStatus().equals("today")) {
            tvClientName.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
            tvTaskTitle.setTextColor(ctx.getResources().getColor(R.color.colorGreen));
        }

        return view;
    }
}
