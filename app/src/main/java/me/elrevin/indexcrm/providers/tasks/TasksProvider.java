package me.elrevin.indexcrm.providers.tasks;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.GetTasksListRequest;
import me.elrevin.indexcrm.rest.models.GetTasksListRequestModel;

public class TasksProvider {

    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    GetTasksListRequest getTasksListRequest;

    public TasksProvider() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadList(int limit, final LoadTasksListHandler handler) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        getTasksListRequest.get(auth, new GetTasksListRequestModel(
                currentUserProvider.getId(), currentUserProvider.getToken(), Integer.toString(limit)
        ).toMap())
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
                                handler.onLoaded();
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
}
