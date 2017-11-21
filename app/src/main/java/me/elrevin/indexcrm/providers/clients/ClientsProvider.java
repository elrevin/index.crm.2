package me.elrevin.indexcrm.providers.clients;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.GetClientsListRequest;
import me.elrevin.indexcrm.rest.models.GetClientsListRequestModel;

public class ClientsProvider {
    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    GetClientsListRequest getClientsListRequest;

    public ClientsProvider() {
        CustomApp.getApplicationComponent().inject(this);
    }

    protected void pLoadList(int limit, String name,  LoadClientsListHandler handler) {
        GetClientsListRequestModel requestModel;

        if (name.isEmpty()) {
            requestModel = new GetClientsListRequestModel(Integer.toString(limit));
        } else {
            requestModel = new GetClientsListRequestModel(Integer.toString(limit), name);
        }

        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        getClientsListRequest.get(auth, requestModel.toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                handler.onLoaded(response.body().getList());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }
                    if (response.code() == 401) {
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. TasksProvider.loadList"));
                });
    }

    public void loadList(int limit, LoadClientsListHandler handler) {
        pLoadList(limit, "", handler);
    }

    public void loadList(int limit, String name,  LoadClientsListHandler handler) {
        pLoadList(limit, name, handler);
    }
}

