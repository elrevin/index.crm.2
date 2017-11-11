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
        tasksProvider.loadList(3, new LoadTasksListHandler() {
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
        });
    }

    public void loadClients() {
        clientsProvider.loadList(3, new LoadClientsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onLoaded(List<ClientModel> list) {
                getViewState().onClientsLoaded(list);
            }

            @Override
            public void onAuthFailure() {

            }
        });
    }

    public  void loadNews() {
        newsProvider.loadList(3, 0, new LoadNewsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onLoaded(List<NewsModel> list) {
                getViewState().onNewsLoaded(list);
            }

            @Override
            public void onAuthFailure() {

            }
        });
    }

    public void openClientsScreen() {
        getViewState().openClientsScreen();
    }
}
