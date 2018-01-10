package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.TaskModel;

public interface MainScreenView extends MvpView {
    void onTasksLoaded(List<TaskModel> list);
    void onClientsLoaded(List<ClientModel> list);
    void onNewsLoaded(List<NewsModel> list);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void login();

    void openClientsScreen();
    void openTasksScreen();

    void onLoadingStart();
    void onLoadingEnd();

    void onGetNet(boolean home);

    void onGetPlushki(Integer count);
}
