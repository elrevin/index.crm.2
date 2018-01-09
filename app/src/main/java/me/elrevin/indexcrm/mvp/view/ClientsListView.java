package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.ClientModel;

public interface ClientsListView extends MvpView {
    void onClientsLoaded(List<ClientModel> list);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void login();
    void onLoadingStart();
    void onLoadingEnd();
}
