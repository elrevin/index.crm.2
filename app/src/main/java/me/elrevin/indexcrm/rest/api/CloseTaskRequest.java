package me.elrevin.indexcrm.rest.api;


import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.CloseTask;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface CloseTaskRequest {
    @FormUrlEncoded
    @POST(ApiMethods.CLOSE_TASK)
    Observable<Response<CloseTask>> put(@Header("Authorization") String authorization, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);
}
