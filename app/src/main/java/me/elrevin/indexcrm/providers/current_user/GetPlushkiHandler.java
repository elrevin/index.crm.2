package me.elrevin.indexcrm.providers.current_user;

import me.elrevin.indexcrm.mvp.model.GetPlushkiModel;

public interface GetPlushkiHandler {
    void onRequestFailure(Throwable t);
    void onGetPlushki(GetPlushkiModel answer);
    void onAuthFailure();
}
