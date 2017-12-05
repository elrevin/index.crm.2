package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class PutNewsCommentRequestDataModel extends BasePutRequestDataModel {

    String text;
    String newsId;
    String replyToId;

    public PutNewsCommentRequestDataModel(String newsId, String text, String replyToId) {
        super();
        this.text = text;
        this.newsId = newsId;
        this.replyToId = replyToId;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("news_id", newsId);
        map.put("text", text);
        map.put("reply_to", replyToId);
    }
}
