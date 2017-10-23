package me.elrevin.indexcrm.common;

public interface CheckAuthHandler {
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void onAuthCorrect();
}
