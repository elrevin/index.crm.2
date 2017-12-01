package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsCommentsListModel extends BaseModel {
    @SerializedName("list")
    @Expose
    private java.util.List<NewsCommentModel> list = null;

    public java.util.List<NewsCommentModel> getList() {
        return list;
    }

    public void setList(java.util.List<NewsCommentModel> list) {
        this.list = list;
    }
}
