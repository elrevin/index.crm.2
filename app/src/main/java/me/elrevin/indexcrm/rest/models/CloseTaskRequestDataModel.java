package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class CloseTaskRequestDataModel extends BasePutRequestDataModel {
    private String id;
    private String comment;
    private boolean createContact;

    public CloseTaskRequestDataModel(String id, String comment, boolean createContact) {
        this.id = id;
        this.comment = comment;
        this.createContact = createContact;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("id", id);
        map.put("comment", comment);
        map.put("createContact", (createContact ? "1" : "0"));
    }
}
