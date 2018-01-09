package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.TaskModel;

public interface TasksListView extends MvpView {
    void onTasksLoaded(List<TaskModel> list);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void login();
    void onLoadingStart();
    void onLoadingEnd();

    void openTask(TaskModel task);
}
