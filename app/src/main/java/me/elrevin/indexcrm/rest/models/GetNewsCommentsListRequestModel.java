package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class GetNewsCommentsListRequestModel extends BaseRequestModel {
    private String newsId;

    public GetNewsCommentsListRequestModel(String newsId) {
        super();
        this.newsId = newsId;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("news_id", newsId);
    }
}
