package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class CheckAuthRequestModel extends BaseRequestModel {
    private String userId;
    private String token;

    public CheckAuthRequestModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("user_id", userId);
        map.put("token", token);
    }
}
