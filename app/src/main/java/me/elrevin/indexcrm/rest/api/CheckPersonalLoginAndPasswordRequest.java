package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.CheckPersonalLoginAndPasswordModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface CheckPersonalLoginAndPasswordRequest {
    @GET(ApiMethods.CHECK_PERSONAL_LOGIN_AND_PASSWORD)
    Observable<Response<CheckPersonalLoginAndPasswordModel>> get(@Header("Authorization") String authorization, @QueryMap Map<String, String> map);
}
