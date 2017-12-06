package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class PutRateNewsCommentRequestDataModel extends BasePutRequestDataModel {
    String newsId;
    String commentId;
    String sign;

    public PutRateNewsCommentRequestDataModel(String newsId, String commentId, String sign) {
        super();
        this.newsId = newsId;
        this.commentId = commentId;
        this.sign = sign;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("news_id", newsId);
        map.put("comment_id", commentId);
        map.put("sign", sign);
    }
}
