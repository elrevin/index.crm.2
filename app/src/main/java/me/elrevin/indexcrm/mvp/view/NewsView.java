package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;


public interface NewsView extends MvpView {
    void login();
    void afterLogin();
    void onNetworkFailure(Throwable t);
}