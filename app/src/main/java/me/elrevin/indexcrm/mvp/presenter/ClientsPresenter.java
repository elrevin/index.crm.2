package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.view.ClientsView;
import me.elrevin.indexcrm.providers.clients.ClientsProvider;
import me.elrevin.indexcrm.providers.clients.LoadClientsListHandler;
import me.elrevin.indexcrm.providers.current_user.CheckAuthHandler;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;

@InjectViewState
public class ClientsPresenter extends MvpPresenter<ClientsView> {
    @Inject
    CurrentUserProvider currentUserProvider;

    public ClientsPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void showClientsList() {
        getViewState().showClientsList();
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
