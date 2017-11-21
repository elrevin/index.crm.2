package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.view.ClientsListView;
import me.elrevin.indexcrm.providers.clients.ClientsProvider;
import me.elrevin.indexcrm.providers.clients.LoadClientsListHandler;

@InjectViewState
public class ClientsListPresenter extends MvpPresenter<ClientsListView> {
    @Inject
    ClientsProvider clientsProvider;

    public ClientsListPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    protected void pLoadClients(String name) {
        LoadClientsListHandler handler = new LoadClientsListHandler() {
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
        };

        if (name.isEmpty()) {
            clientsProvider.loadList(30, handler);
        } else {
            clientsProvider.loadList(30, name, handler);
        }
    }

    public void loadClients() {
        pLoadClients("");
    }

    public void loadClients(String name) {
        pLoadClients(name);
    }
}
