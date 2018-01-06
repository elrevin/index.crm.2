package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.TasksPresenter;
import me.elrevin.indexcrm.mvp.view.TasksView;
import me.elrevin.indexcrm.ui.fragment.TaskItemFragment;
import me.elrevin.indexcrm.ui.fragment.TasksListFragment;

public class TasksActivity extends BaseActivity implements TasksView {

    boolean oneTask = false;

    @InjectPresenter
    TasksPresenter presenter;

    TasksListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("TASK_ITEM")) {
            oneTask = true;
            TaskModel item = (TaskModel) intent.getSerializableExtra("TASK_ITEM");
            openTask(item);
        } else {
            oneTask = false;
            presenter.showList();
        }
    }

    @Override
    public boolean setDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public void showList() {
        listFragment = new TasksListFragment();
        setContent(listFragment);
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

    public void onCloseTask(String id) {
        if (oneTask) {
            Intent intent = getIntent();
            intent.putExtra("id", id);
            setResult(1, intent);
            finish();
        } else {
            removeCurrentFragment();
            if (listFragment != null) {
                listFragment.onCloseTask(id);
            }
        }
    }


}
