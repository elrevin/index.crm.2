package me.elrevin.indexcrm.providers.clients;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.ClientModel;

public interface LoadClientsListHandler {
    void onRequestFailure(Throwable t);
    void onLoaded(List<ClientModel> list);
    void onAuthFailure();
}
