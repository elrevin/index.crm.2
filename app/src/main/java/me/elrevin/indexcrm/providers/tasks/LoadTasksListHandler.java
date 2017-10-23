package me.elrevin.indexcrm.providers.tasks;

public interface LoadTasksListHandler {
    void onRequestFailure(Throwable t);
    void onLoaded();
    void onAuthFailure();
}
