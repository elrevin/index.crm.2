package me.elrevin.indexcrm.mvp.presenter;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.view.TaskItemView;
import me.elrevin.indexcrm.providers.tasks.CloseTaskHandler;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;
import me.elrevin.indexcrm.ui.activity.TasksActivity;

@InjectViewState
public class TaskItemPresenter extends MvpPresenter<TaskItemView> {

    @Inject
    TasksProvider provider;

    public TaskItemPresenter() {
        super();
        CustomApp.getApplicationComponent().inject(this);
    }

    public void closeTask(boolean createContact, String comment, TaskModel task) {
        getViewState().onLoadingStart();
        CloseTaskHandler handler = new CloseTaskHandler() {
            @Override
            public void onRequestFailure(Throwable t) {
                getViewState().onRequestFailure(t);
            }

            @Override
            public void onClosed(String id) {
                getViewState().onLoadingEnd();
                getViewState().onTaskClosed(id);
            }

            @Override
            public void onAuthFailure() {
                getViewState().onLoadingEnd();
                getViewState().onAuthFailure();
            }
        };

        provider.closeTask(createContact, comment, task, handler);
    }
}
