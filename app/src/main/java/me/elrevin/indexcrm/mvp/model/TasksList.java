package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TasksList extends Base {

    @SerializedName("list")
    @Expose
    private java.util.List<Task> list = null;

    public java.util.List<Task> getList() {
        return list;
    }

    public void setList(java.util.List<Task> list) {
        this.list = list;
    }

}