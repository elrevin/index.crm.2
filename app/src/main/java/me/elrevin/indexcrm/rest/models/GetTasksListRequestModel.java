package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class GetTasksListRequestModel extends BaseRequestModel {
    private String userId;
    private String token;
    private String limit;

    public GetTasksListRequestModel(String userId, String token, String limit) {
        this.userId = userId;
        this.token = token;
        this.limit = limit;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("user_id", userId);
        map.put("token", token);
        map.put("limit", limit);
    }
}
