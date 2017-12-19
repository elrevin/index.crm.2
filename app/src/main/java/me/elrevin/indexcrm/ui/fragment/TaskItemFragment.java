package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.TaskItemPresenter;
import me.elrevin.indexcrm.mvp.view.TaskItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskItemFragment extends BaseFragment implements TaskItemView {

    private TaskModel task;

    @InjectPresenter
    TaskItemPresenter presenter;

    @BindView(R.id.tvPlaner)
    TextView tvPlaner;

    @BindView(R.id.tvEndDate)
    TextView tvEndDate;

    @BindView(R.id.tvClientName)
    TextView tvClientName;

    @BindView(R.id.tvTaskTitle)
    TextView tvTaskTitle;

    @BindView(R.id.llPlanerComment)
    LinearLayout llPlanerComment;

    @BindView(R.id.tvPlanerComment)
    TextView tvPlanerComment;

    public TaskItemFragment() {
        // Required empty public constructor
    }

    public void setTask(TaskModel task) {
        this.task = task;
        presenter.setItem(task);
        presenter.showTask();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_task_item;
    }

    @Override
    public String onCreateToolbarTitle() {
        return task.getPlanedEndDateF()+" "+task.getClient();
    }

    @Override
    public void showTask() {
        if (task != null) {
            tvClientName.setText(task.getClient());
            tvEndDate.setText(task.getPlanedEndDateF());
            tvPlaner.setText(task.getPlaner());

            if (task.getPlanedComment() != null && !task.getPlanedComment().isEmpty()) {
                llPlanerComment.setVisibility(View.VISIBLE);
                tvPlanerComment.setText(task.getPlanedComment());
            } else {
                llPlanerComment.setVisibility(View.GONE);
                tvPlanerComment.setText("");
            }
        } else {
            tvClientName.setText("");
            tvEndDate.setText("");
            tvPlaner.setText("");
            tvPlanerComment.setText("");
            llPlanerComment.setVisibility(View.GONE);
        }
    }
}
