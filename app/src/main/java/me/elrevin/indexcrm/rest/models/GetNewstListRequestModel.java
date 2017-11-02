package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class GetNewstListRequestModel extends BaseRequestModel {
    private String limit;
    private String from;


    public GetNewstListRequestModel(String limit, String from) {
        super();
        this.limit = limit;
        this.from = from;
    }

    public GetNewstListRequestModel(int limit, int from) {
        super();
        this.limit = Integer.toString(limit);
        this.from = Integer.toString(from);
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("limit", limit);
        map.put("from", from);
    }
}
