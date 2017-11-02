package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewstListModel extends BaseModel {
    @SerializedName("list")
    @Expose
    private java.util.List<NewsModel> list = null;

    public java.util.List<NewsModel> getList() {
        return list;
    }

    public void setList(java.util.List<NewsModel> list) {
        this.list = list;
    }
}
