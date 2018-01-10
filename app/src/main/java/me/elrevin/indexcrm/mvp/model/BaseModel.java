package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CustomActivityManager;

public class BaseModel implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("in_home_net")
    @Expose
    private boolean inHomeNet;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean getInHomeNet() {
        return inHomeNet;
    }

    public void setInHomeNet(boolean inHomeNet) {
        this.inHomeNet = inHomeNet;
    }
}