package me.elrevin.indexcrm.common;

public interface CheckCommonLoginAndPasswordHandler {
    void onRequestFailure(Throwable t);
    void onLoginOrPasswordIncorrect();
    void onLoginAndPasswordCorrect();
}
