package me.elrevin.indexcrm.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.TasksPresenter;
import me.elrevin.indexcrm.mvp.view.TasksView;
import me.elrevin.indexcrm.ui.fragment.TaskItemFragment;
import me.elrevin.indexcrm.ui.fragment.TasksListFragment;

public class TasksActivity extends BaseActivity implements TasksView {

    @InjectPresenter
    TasksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.showList();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public void showList() {
        setContent(new TasksListFragment());
    }

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    public void openTask(TaskModel task) {
        TaskItemFragment fragment = new TaskItemFragment();
        fragment.setTask(task);
        addContent(fragment);
    }
}
