package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;


public interface TasksView extends MvpView {
    void showList();
    void login();
    void afterLogin();
    void onNetworkFailure(Throwable t);
}
