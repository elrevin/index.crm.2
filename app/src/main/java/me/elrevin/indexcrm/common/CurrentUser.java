package me.elrevin.indexcrm.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import me.elrevin.indexcrm.CustomApp;

public class CurrentUser {
    private String id;
    private String token;
    private String login;
    private String password;

    private SharedPreferences preferences;

    public CurrentUser(CustomApp app) {
        preferences = PreferenceManager.getDefaultSharedPreferences(app);

        login = preferences.getString("login", null);
        password = preferences.getString("password", null);
        token = preferences.getString("token", null);
        id = preferences.getString("userId", null);

    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userId", login);
        editor.apply();
        editor.commit();
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", login);
        editor.apply();
        editor.commit();
    }

    public void setLogin(String login) {
        this.login = login;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", login);
        editor.apply();
        editor.commit();
    }

    public void setPassword(String password) {
        this.password = password;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", login);
        editor.apply();
        editor.commit();
    }

    public boolean isAuth() {
        if (getLogin() == null || getPassword() == null || getId() == null || getToken() == null) {
            return false;
        }

        return true;
    }
}
