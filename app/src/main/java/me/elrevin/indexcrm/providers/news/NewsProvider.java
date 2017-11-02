package me.elrevin.indexcrm.providers.news;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.GetNewstListRequest;
import me.elrevin.indexcrm.rest.models.GetNewstListRequestModel;

public class NewsProvider {
    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    GetNewstListRequest getNewstListRequest;

    public NewsProvider() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadList(int limit, int from, final LoadNewsListHandler handler) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        getNewstListRequest.get(auth, new GetNewstListRequestModel(limit, from).toMap())
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
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. News.loadList"));
                });
    }
}
