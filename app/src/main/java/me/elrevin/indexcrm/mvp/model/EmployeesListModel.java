package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeesListModel extends BaseModel {
    @SerializedName("list")
    @Expose
    private java.util.List<EmployeModel> list = null;

    public java.util.List<EmployeModel> getList() {
        return list;
    }

    public void setList(java.util.List<EmployeModel> list) {
        this.list = list;
    }
}
