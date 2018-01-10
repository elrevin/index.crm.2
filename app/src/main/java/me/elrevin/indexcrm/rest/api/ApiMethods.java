package me.elrevin.indexcrm.rest.api;

import android.util.Base64;

public class ApiMethods {
    static final String CHECK_LOGIN_AND_PASSWORD = "api.php?module=testlogin";
    static final String CHECK_PERSONAL_LOGIN_AND_PASSWORD = "api.php?module=users&method=createTokenByLogin";
    static final String CHECK_AUTH = "api.php?module=users&method=auth";
    static final String GET_TASKS_LIST = "api.php?module=tasks&method=getList";
    static final String CLOSE_TASK = "api.php?module=tasks&method=closeTask";
    static final String GET_CLIENTS_LIST = "api.php?module=clients&method=getList";
    static final String GET_NEWS_LIST = "api.php?module=news&method=getList";
    static final String GET_NEWS_COMMENTS_LIST = "api.php?module=news&method=getComments";
    static final String PUT_NEWS_COMMENT = "api.php?module=news&method=putComment";
    static final String PUT_NEWS_COMMENT_RATE = "api.php?module=news&method=putRate";
    static final String GET_NET = "api.php?module=users&method=getNet";
    static final String GET_PLUSHKI = "api.php?module=users&method=getPlushki";

    public static final String getBasicAuthString(String login, String password) {
        return "Basic " + Base64.encodeToString(String.format("%s:%s", login, password).getBytes(), Base64.NO_WRAP);
    }
}
