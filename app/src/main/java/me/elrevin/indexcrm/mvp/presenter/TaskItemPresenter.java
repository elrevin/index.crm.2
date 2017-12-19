package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.view.TaskItemView;

@InjectViewState
public class TaskItemPresenter extends MvpPresenter<TaskItemView> {
    private TaskModel task;

    public TaskItemPresenter() {
        super();
    }

    public void setItem(TaskModel task) {
        this.task = task;
    }

    public void showTask() {
        getViewState().showTask();
    }
}
