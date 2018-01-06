package me.elrevin.indexcrm.providers.tasks;

import me.elrevin.indexcrm.mvp.model.TaskModel;

public interface CloseTaskHandler {
    void onRequestFailure(Throwable t);
    void onClosed(String id);
    void onAuthFailure();
}
