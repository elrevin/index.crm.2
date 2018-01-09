package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.view.MainScreenView;
import me.elrevin.indexcrm.providers.clients.ClientsProvider;
import me.elrevin.indexcrm.providers.clients.LoadClientsListHandler;
import me.elrevin.indexcrm.providers.news.LoadNewsListHandler;
import me.elrevin.indexcrm.providers.news.NewsProvider;
import me.elrevin.indexcrm.providers.tasks.LoadTasksListHandler;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;

@InjectViewState
public class MainScreenPresenter extends MvpPresenter <MainScreenView> {
    @Inject
    TasksProvider tasksProvider;

    @Inject
    ClientsProvider clientsProvider;

    @Inject
    NewsProvider newsProvider;

    public MainScreenPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadTasks() {
        getViewState().onLoadingStart();
        tasksProvider.loadList(3, new LoadTasksListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {
                getViewState().onRequestFailure(t);
            }

            @Override
            public void onLoaded(List<TaskModel> list) {
                getViewState().onLoadingEnd();
                getViewState().onTasksLoaded(list);
            }

            @Override
            public void onAuthFailure() {
                getViewState().onLoadingEnd();
                getViewState().onAuthFailure();
            }
        });
    }

    public void loadClients() {
        getViewState().onLoadingStart();
        clientsProvider.loadList(3, new LoadClientsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {
                getViewState().onRequestFailure(t);
            }

            @Override
            public void onLoaded(List<ClientModel> list) {
                getViewState().onLoadingEnd();
                getViewState().onClientsLoaded(list);
            }

            @Override
            public void onAuthFailure() {
                getViewState().onLoadingEnd();
                getViewState().onAuthFailure();
            }
        });
    }

    public  void loadNews() {
        getViewState().onLoadingStart();
        newsProvider.loadList(new LoadNewsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {
                getViewState().onRequestFailure(t);
            }

            @Override
            public void onLoaded(List<NewsModel> list) {
                getViewState().onLoadingEnd();
                getViewState().onNewsLoaded(list);
            }

            @Override
            public void onAuthFailure() {
                getViewState().onLoadingEnd();
                getViewState().onAuthFailure();
            }
        });
    }

    public void openClientsScreen() {
        getViewState().openClientsScreen();
    }

    public void openTasksScreen() {
        getViewState().openTasksScreen();
    }
}
