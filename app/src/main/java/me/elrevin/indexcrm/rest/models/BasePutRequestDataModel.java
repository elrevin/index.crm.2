package me.elrevin.indexcrm.rest.models;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePutRequestDataModel {
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        onMapCreate(map);

        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);
}
