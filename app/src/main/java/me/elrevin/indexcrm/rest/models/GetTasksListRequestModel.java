package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class GetTasksListRequestModel extends BaseRequestModel {
    private String limit;

    public GetTasksListRequestModel(String limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("limit", limit);
    }
}
