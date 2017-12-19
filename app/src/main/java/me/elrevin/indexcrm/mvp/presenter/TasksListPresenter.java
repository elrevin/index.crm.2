package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.view.TasksListView;
import me.elrevin.indexcrm.providers.tasks.LoadTasksListHandler;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;


@InjectViewState
public class TasksListPresenter extends MvpPresenter<TasksListView> {
    @Inject
    TasksProvider tasksProvider;

    public TasksListPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadTasks() {
        LoadTasksListHandler handler = new LoadTasksListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onLoaded(List<TaskModel> list) {
                getViewState().onTasksLoaded(list);
            }

            @Override
            public void onAuthFailure() {

            }
        };

        tasksProvider.loadList(50, handler);
    }

    public void openTask(TaskModel task) {
        getViewState().openTask(task);
    }
}
