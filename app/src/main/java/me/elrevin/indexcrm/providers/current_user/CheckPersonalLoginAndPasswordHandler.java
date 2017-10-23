package me.elrevin.indexcrm.providers.current_user;

public interface CheckPersonalLoginAndPasswordHandler {
    void onRequestFailure(Throwable t);
    void onLoginOrPasswordIncorrect();
    void onLoginAndPasswordCorrect();
    void onCommonAuthFailure();
}
