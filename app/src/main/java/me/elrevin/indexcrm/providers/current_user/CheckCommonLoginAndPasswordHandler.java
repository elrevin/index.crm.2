package me.elrevin.indexcrm.providers.current_user;

public interface CheckCommonLoginAndPasswordHandler {
    void onRequestFailure(Throwable t);
    void onLoginOrPasswordIncorrect();
    void onLoginAndPasswordCorrect();
}
