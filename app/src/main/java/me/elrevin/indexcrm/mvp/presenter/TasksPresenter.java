package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.view.TasksView;
import me.elrevin.indexcrm.providers.current_user.CheckAuthHandler;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;

@InjectViewState
public class TasksPresenter extends MvpPresenter<TasksView> {
    @Inject
    CurrentUserProvider currentUserProvider;

    public TasksPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void showList() {
        getViewState().showList();
    }
    public void checkAuth() {
        if (currentUserProvider.haveAuthData()) {
            currentUserProvider.checkAuth(new CheckAuthHandler() {
                @Override
                public void onRequestFailure(Throwable t) {
                    getViewState().onNetworkFailure(t);
                }

                @Override
                public void onAuthFailure() {
                    getViewState().login();
                }

                @Override
                public void onAuthCorrect() {
                    getViewState().afterLogin();
                }
            });
        } else {
            getViewState().login();
        }
    }
}
