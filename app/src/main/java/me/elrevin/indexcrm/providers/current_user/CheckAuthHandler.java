package me.elrevin.indexcrm.providers.current_user;

public interface CheckAuthHandler {
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void onAuthCorrect();
}
