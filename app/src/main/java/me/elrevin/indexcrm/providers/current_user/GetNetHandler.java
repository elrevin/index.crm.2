package me.elrevin.indexcrm.providers.current_user;

import me.elrevin.indexcrm.mvp.model.GetNetModel;

public interface GetNetHandler {
    void onRequestFailure(Throwable t);
    void onGetNet(GetNetModel answer);
    void onAuthFailure();
}
