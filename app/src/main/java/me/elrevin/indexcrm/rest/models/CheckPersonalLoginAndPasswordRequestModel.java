package me.elrevin.indexcrm.rest.models;

import java.util.Map;

public class CheckPersonalLoginAndPasswordRequestModel extends BaseRequestModel {
    private String login;
    private String password;

    public CheckPersonalLoginAndPasswordRequestModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("login", login);
        map.put("password", password);
    }
}
