package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientsListModel extends BaseModel {
    @SerializedName("list")
    @Expose
    private java.util.List<ClientModel> list = null;

    public java.util.List<ClientModel> getList() {
        return list;
    }

    public void setList(java.util.List<ClientModel> list) {
        this.list = list;
    }
}
