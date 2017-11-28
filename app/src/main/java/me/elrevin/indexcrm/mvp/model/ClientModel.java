package me.elrevin.indexcrm.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClientModel extends BaseListItemModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("last_contact_date")
    @Expose
    private String lastContactDate;
    @SerializedName("contacts_count")
    @Expose
    private String contactsCount;
    @SerializedName("contracts")
    @Expose
    private java.util.List<ContractModel> contractModels = null;
    @SerializedName("services")
    @Expose
    private java.util.List<String> services = null;
    @SerializedName("persons")
    @Expose
    private java.util.List<PersonModel> persons = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPhone() {
        return phone;
    }

    public void setLastContactDate(String lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public void setContactsCount(String contactsCount) {
        this.contactsCount = contactsCount;
    }

    public String getLastContactDate() {
        return lastContactDate;
    }

    public String getContactsCount() {
        return contactsCount;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public java.util.List<ContractModel> getContractModels() {
        return contractModels;
    }

    public void setContractModels(java.util.List<ContractModel> contractModels) {
        this.contractModels = contractModels;
    }

    public java.util.List<String> getServices() {
        return services;
    }

    public void setServices(java.util.List<String> services) {
        this.services = services;
    }

    public java.util.List<PersonModel> getPersons() {
        return persons;
    }

    public void setPersons(java.util.List<PersonModel> persons) {
        this.persons = persons;
    }}
