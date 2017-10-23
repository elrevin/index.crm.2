package me.elrevin.indexcrm.common;

public interface CheckPersonalLoginAndPasswordHandler {
    void onRequestFailure(Throwable t);
    void onLoginOrPasswordIncorrect();
    void onLoginAndPasswordCorrect();
    void onCommonAuthFailure();
}
