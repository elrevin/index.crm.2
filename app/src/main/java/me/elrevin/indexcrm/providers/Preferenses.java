package me.elrevin.indexcrm.providers;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

@Singleton
public class Preferenses {
    private SharedPreferences pref;

    private Context appContext;

    private String login, password, token, userId;
}
