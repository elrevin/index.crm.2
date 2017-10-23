package me.elrevin.indexcrm.rest.models;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CurrentUser;

public abstract class BaseRequestModel {

    @Inject
    public CurrentUser currentUser;

    protected String method;
    protected String module;
    protected String userId;
    protected String token;

    BaseRequestModel() {
        CustomApp.getApplicationComponent().inject(this);

        userId = this.currentUser.getId();
        token = this.currentUser.getToken();
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if (method != null) {
            map.put("method", method);
        }
        if (module != null) {
            map.put("module", module);
        }
        if (token != null) {
            map.put("token", token);
        }

        onMapCreate(map);

        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);
}
