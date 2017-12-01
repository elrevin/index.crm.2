package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsCommentModel extends BaseListItemModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("nest")
    @Expose
    private String nest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNest() {
        return Integer.getInteger(nest);
    }

    public void setNest(String nest) {
        this.nest = nest;
    }
}
