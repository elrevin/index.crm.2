package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.view.TasksView;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;

@InjectViewState
public class TasksPresenter extends MvpPresenter<TasksView> {
    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    TasksProvider tasksProvider;

    public TasksPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void showList() {
        getViewState().showList();
    }
}
