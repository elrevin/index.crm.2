package me.elrevin.indexcrm.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.Base;
import me.elrevin.indexcrm.mvp.model.CheckAuth;
import me.elrevin.indexcrm.mvp.model.CheckPersonalLoginAndPassword;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.CheckAuthRequest;
import me.elrevin.indexcrm.rest.api.CheckCommonLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.CheckPersonalLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.models.CheckAuthRequestModel;
import me.elrevin.indexcrm.rest.models.CheckPersonalLoginAndPasswordRequestModel;
import retrofit2.Response;

public class CurrentUser {
    private String id;
    private String token;
    private String login;
    private String password;

    @Inject
    public CheckCommonLoginAndPasswordRequest checkCommonLoginAndPasswordRequest;

    @Inject
    public CheckPersonalLoginAndPasswordRequest checkPersonalLoginAndPasswordRequest;

    @Inject
    public CheckAuthRequest checkAuthRequest;

    private SharedPreferences preferences;

    public CurrentUser(CustomApp app) {
        CustomApp.getApplicationComponent().inject(this);

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
        editor.putString("userId", id);
        editor.apply();
        editor.commit();
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
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
        editor.putString("password", password);
        editor.apply();
        editor.commit();
    }

    public boolean haveAuthData() {
        if (getLogin() == null || getPassword() == null || getId() == null || getToken() == null) {
            return false;
        }

        return true;
    }

    public void checkCommonLoginAndPassword(final CheckCommonLoginAndPasswordHandler handler) {
        String auth = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        checkCommonLoginAndPasswordRequest.get(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Response<Base>>() {
                    @Override
                    public Response<Base> apply(@NonNull Throwable throwable) throws Exception {
                        handler.onRequestFailure(throwable);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null && response.body().getStatus()) {
                        handler.onLoginAndPasswordCorrect();
                    } else {
                        handler.onLoginOrPasswordIncorrect();
                    }
                });
    }

    public void checkPersonalLoginAndPassword(String login, String password, final CheckPersonalLoginAndPasswordHandler handler) {
        String authStr = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        checkPersonalLoginAndPasswordRequest.get(authStr, new CheckPersonalLoginAndPasswordRequestModel(login, password).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Response<CheckPersonalLoginAndPassword>>() {
                    @Override
                    public Response<CheckPersonalLoginAndPassword> apply(@NonNull Throwable throwable) throws Exception {
                        handler.onRequestFailure(throwable);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            handler.onLoginAndPasswordCorrect();
                            setId(response.body().getId());
                            setToken(response.body().getToken());
                        } else {
                            handler.onLoginOrPasswordIncorrect();
                        }
                    } else {
                        if (response.code() == 401) {
                            handler.onCommonAuthFailure();
                        } else {
                            handler.onRequestFailure(null);
                        }
                    }
                });
    }

    public void checkAuth(final CheckAuthHandler handler) {
        String authStr = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        checkAuthRequest.get(authStr, new CheckAuthRequestModel(getId(), getToken()).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Response<CheckAuth>>() {
                    @Override
                    public Response<CheckAuth> apply(@NonNull Throwable throwable) throws Exception {
                        handler.onRequestFailure(throwable);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            handler.onAuthCorrect();
                        } else {
                            handler.onAuthFailure();
                        }
                    } else {
                        if (response.code() == 401) {
                            handler.onAuthFailure();
                        } else {
                            handler.onRequestFailure(null);
                        }
                    }
                });
    }
}
