package me.elrevin.indexcrm.providers.tasks;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CustomActivityManager;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.CloseTaskRequest;
import me.elrevin.indexcrm.rest.api.GetTasksListRequest;
import me.elrevin.indexcrm.rest.models.CloseTaskRequestDataModel;
import me.elrevin.indexcrm.rest.models.CloseTaskRequestModel;
import me.elrevin.indexcrm.rest.models.GetTasksListRequestModel;

public class TasksProvider {

    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    GetTasksListRequest getTasksListRequest;

    @Inject
    CloseTaskRequest closeTaskRequest;

    @Inject
    CustomActivityManager activityManager;

    public TasksProvider() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadList(int limit, final LoadTasksListHandler handler) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        getTasksListRequest.get(auth, new GetTasksListRequestModel(Integer.toString(limit)).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    activityManager.onHomeNetHandler(false);
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                activityManager.onHomeNetHandler(response.body().getInHomeNet());
                                handler.onLoaded(response.body().getList());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                activityManager.onHomeNetHandler(false);
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }

                    if (response.code() == 401) {
                        activityManager.onHomeNetHandler(false);
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. TasksProvider.loadList"));
                });
    }

    public void closeTask(boolean createContact, String comment, TaskModel task, CloseTaskHandler handler) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        closeTaskRequest.put(auth, new CloseTaskRequestModel().toMap(), new CloseTaskRequestDataModel(task.getId(), comment, createContact).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    activityManager.onHomeNetHandler(false);
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                activityManager.onHomeNetHandler(response.body().getInHomeNet());
                                handler.onClosed(response.body().getId());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                activityManager.onHomeNetHandler(false);
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }

                    if (response.code() == 401) {
                        activityManager.onHomeNetHandler(false);
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. TasksProvider.loadList"));
                });
    }
}
