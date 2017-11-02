package me.elrevin.indexcrm.rest.api;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.BaseModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CheckCommonLoginAndPasswordRequest {
    @GET(ApiMethods.CHECK_LOGIN_AND_PASSWORD)
    Observable<Response<BaseModel>> get(@Header("Authorization") String authorization);
}
