package me.elrevin.indexcrm.providers.tasks;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.TaskModel;

public interface LoadTasksListHandler {
    void onRequestFailure(Throwable t);
    void onLoaded(List<TaskModel> list);
    void onAuthFailure();
}
