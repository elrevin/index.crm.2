package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutRateNewsCommentModel extends BaseModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("raiting")
    @Expose
    private String raiting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }
}
