package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.TaskItemPresenter;
import me.elrevin.indexcrm.mvp.view.TaskItemView;
import me.elrevin.indexcrm.providers.tasks.CloseTaskHandler;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;
import me.elrevin.indexcrm.ui.activity.TasksActivity;

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

    @BindView(R.id.edtComment)
    EditText edtComment;

    @BindView(R.id.btnClose)
    Button btnClose;

    @BindView(R.id.btnCloseByContact)
    Button btnCloseByContact;

    @Inject
    TasksProvider provider;

    public TaskItemFragment() {
        // Required empty public constructor
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showTask();
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
        tvClientName.setText("");
        tvEndDate.setText("");
        tvPlaner.setText("");
        tvTaskTitle.setText("");
        tvPlanerComment.setText("");
        llPlanerComment.setVisibility(View.GONE);
        if (task != null) {
            tvClientName.setText(task.getClient());
            tvEndDate.setText(task.getPlanedEndDateF());
            tvPlaner.setText(task.getPlaner());
            tvTaskTitle.setText(task.getTitle());

            if (task.getPlanedComment() != null && !task.getPlanedComment().isEmpty()) {
                llPlanerComment.setVisibility(View.VISIBLE);
                tvPlanerComment.setText(task.getPlanedComment());
            } else {
                llPlanerComment.setVisibility(View.GONE);
                tvPlanerComment.setText("");
            }
        }
    }

    @Override
    public void onTaskClosed(String id) {
        ((TasksActivity) getBaseActivity()).onCloseTask(id);
    }

    @OnClick(R.id.btnClose)
    void onBtnCloseClick(View v) {
        if (task != null) {
            String comment = edtComment.getText().toString();
            presenter.closeTask(false, comment, task);
        }
    }
    @OnClick(R.id.btnCloseByContact)
    void onBtnCloseByContactClick(View v) {
        if (task != null) {
            String comment = edtComment.getText().toString();
            presenter.closeTask(true, comment, task);
        }
    }

}
