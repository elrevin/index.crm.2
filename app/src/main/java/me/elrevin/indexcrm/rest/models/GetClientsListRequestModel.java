package me.elrevin.indexcrm.rest.models;

import java.util.Map;

import javax.inject.Inject;

import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;

public class GetClientsListRequestModel extends BaseRequestModel {
    private String limit;
    private String name;

    public GetClientsListRequestModel(String limit, String name) {
        super();
        this.limit = limit;
        this.name = name;
    }

    public GetClientsListRequestModel(String limit) {
        super();
        this.limit = limit;
        this.name = "";
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("limit", limit);
        map.put("name", name);
    }
}
