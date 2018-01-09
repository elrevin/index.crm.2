package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import me.elrevin.indexcrm.mvp.model.TaskModel;


public interface TaskItemView extends MvpView {
    void showTask();
    void onTaskClosed(String id);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void login();
    void onLoadingStart();
    void onLoadingEnd();
}
