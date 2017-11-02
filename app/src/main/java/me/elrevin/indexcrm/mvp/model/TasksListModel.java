package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TasksListModel extends BaseModel {

    @SerializedName("list")
    @Expose
    private java.util.List<TaskModel> list = null;

    public java.util.List<TaskModel> getList() {
        return list;
    }

    public void setList(java.util.List<TaskModel> list) {
        this.list = list;
    }

}