package me.elrevin.indexcrm.rest.api;

import android.util.Base64;

public class ApiMethods {
    public static final String CHECK_LOGIN_AND_PASSWORD = "api.php?module=testlogin";
    public static final String CHECK_PERSONAL_LOGIN_AND_PASSWORD = "api.php?module=users&method=createTokenByLogin";
    public static final String CHECK_AUTH = "api.php?module=users&method=auth";

    public static final String getBasicAuthString(String login, String password) {
        return "Basic " + Base64.encodeToString(String.format("%s:%s", login, password).getBytes(), Base64.NO_WRAP);
    }
}
