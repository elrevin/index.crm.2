package me.elrevin.indexcrm.providers.current_user;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CustomActivityManager;
import me.elrevin.indexcrm.mvp.model.BaseModel;
import me.elrevin.indexcrm.mvp.model.CheckAuthModel;
import me.elrevin.indexcrm.mvp.model.CheckPersonalLoginAndPasswordModel;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.CheckAuthRequest;
import me.elrevin.indexcrm.rest.api.CheckCommonLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.CheckPersonalLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.GetNetRequest;
import me.elrevin.indexcrm.rest.api.GetPlushkiRequest;
import me.elrevin.indexcrm.rest.models.CheckAuthRequestModel;
import me.elrevin.indexcrm.rest.models.CheckPersonalLoginAndPasswordRequestModel;
import me.elrevin.indexcrm.rest.models.GetNetRequestModel;
import retrofit2.Response;

public class CurrentUserProvider {
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

    @Inject
    public GetNetRequest getNetRequest;

    @Inject
    public GetPlushkiRequest getPlushkiRequest;

    @Inject
    CustomActivityManager activityManager;

    private SharedPreferences preferences;

    public CurrentUserProvider(CustomApp app) {
        CustomApp.getApplicationComponent().inject(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(app);

        login = preferences.getString("login", null);
        password = preferences.getString("password", null);
        token = preferences.getString("token", null);
        Log.i("index.crm", "token: "+token);
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
                .onErrorReturn(new Function<Throwable, Response<BaseModel>>() {
                    @Override
                    public Response<BaseModel> apply(@NonNull Throwable throwable) throws Exception {
                        handler.onRequestFailure(throwable);
                        activityManager.onHomeNetHandler(false);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null && response.body().getStatus()) {
                        activityManager.onHomeNetHandler(response.body().getInHomeNet());
                        handler.onLoginAndPasswordCorrect();
                    } else {
                        activityManager.onHomeNetHandler(false);
                        handler.onLoginOrPasswordIncorrect();
                    }
                });
    }

    public void checkPersonalLoginAndPassword(String login, String password, final CheckPersonalLoginAndPasswordHandler handler) {
        String authStr = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        checkPersonalLoginAndPasswordRequest.get(authStr, new CheckPersonalLoginAndPasswordRequestModel(login, password).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Response<CheckPersonalLoginAndPasswordModel>>() {
                    @Override
                    public Response<CheckPersonalLoginAndPasswordModel> apply(@NonNull Throwable throwable) throws Exception {
                        activityManager.onHomeNetHandler(false);
                        handler.onRequestFailure(throwable);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            activityManager.onHomeNetHandler(response.body().getInHomeNet());
                            handler.onLoginAndPasswordCorrect();
                            setId(response.body().getId());
                            setToken(response.body().getToken());
                        } else {
                            activityManager.onHomeNetHandler(false);
                            handler.onLoginOrPasswordIncorrect();
                        }
                    } else {
                        if (response.code() == 401) {
                            activityManager.onHomeNetHandler(false);
                            handler.onCommonAuthFailure();
                        } else {
                            activityManager.onHomeNetHandler(false);
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
                .onErrorReturn(new Function<Throwable, Response<CheckAuthModel>>() {
                    @Override
                    public Response<CheckAuthModel> apply(@NonNull Throwable throwable) throws Exception {
                        activityManager.onHomeNetHandler(false);
                        handler.onRequestFailure(throwable);
                        return null;
                    }
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            activityManager.onHomeNetHandler(response.body().getInHomeNet());
                            handler.onAuthCorrect();
                        } else {
                            activityManager.onHomeNetHandler(false);
                            handler.onAuthFailure();
                        }
                    } else {
                        if (response.code() == 401) {
                            activityManager.onHomeNetHandler(false);
                            handler.onAuthFailure();
                        } else {
                            activityManager.onHomeNetHandler(false);
                            handler.onRequestFailure(null);
                        }
                    }
                });
    }

    public void getNet( final GetNetHandler handler) {
        String authStr = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        getNetRequest.get(authStr, new GetNetRequestModel().toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            handler.onGetNet(response.body());
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

    public void getPlushki( final GetPlushkiHandler handler) {
        String authStr = ApiMethods.getBasicAuthString(getLogin(), getPassword());
        getPlushkiRequest.get(authStr, new GetNetRequestModel().toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getStatus()) {
                            handler.onGetPlushki(response.body());
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
