package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("planer")
    @Expose
    private String planer;
    @SerializedName("controller")
    @Expose
    private String controller;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time_delta")
    @Expose
    private Integer timeDelta;
    @SerializedName("time_status")
    @Expose
    private String timeStatus;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("planed_date")
    @Expose
    private String planedDate;
    @SerializedName("planed_enddate")
    @Expose
    private String planedEnddate;
    @SerializedName("planed_end_date_f")
    @Expose
    private String planedEndDateF;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("person_second_name")
    @Expose
    private String personSecondName;
    @SerializedName("person_first_name")
    @Expose
    private String personFirstName;
    @SerializedName("person_phone")
    @Expose
    private String personPhone;
    @SerializedName("planed_comment")
    @Expose
    private String planedComment;
    @SerializedName("td")
    @Expose
    private Integer td;
    @SerializedName("tm")
    @Expose
    private Integer tm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaner() {
        return planer;
    }

    public void setPlaner(String planer) {
        this.planer = planer;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTimeDelta() {
        return timeDelta;
    }

    public void setTimeDelta(Integer timeDelta) {
        this.timeDelta = timeDelta;
    }

    public String getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(String timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlanedDate() {
        return planedDate;
    }

    public void setPlanedDate(String planedDate) {
        this.planedDate = planedDate;
    }

    public String getPlanedEnddate() {
        return planedEnddate;
    }

    public void setPlanedEnddate(String planedEnddate) {
        this.planedEnddate = planedEnddate;
    }

    public String getPlanedEndDateF() {
        return planedEndDateF;
    }

    public void setPlanedEndDateF(String planedEndDateF) {
        this.planedEndDateF = planedEndDateF;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPersonSecondName() {
        return personSecondName;
    }

    public void setPersonSecondName(String personSecondName) {
        this.personSecondName = personSecondName;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPlanedComment() {
        return planedComment;
    }

    public void setPlanedComment(String planedComment) {
        this.planedComment = planedComment;
    }

    public Integer getTd() {
        return td;
    }

    public void setTd(Integer td) {
        this.td = td;
    }

    public Integer getTm() {
        return tm;
    }

    public void setTm(Integer tm) {
        this.tm = tm;
    }

}