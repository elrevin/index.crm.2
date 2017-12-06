package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.PutNewsCommentModel;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface PutNewsCommentRequest {
    @FormUrlEncoded
    @POST(ApiMethods.PUT_NEWS_COMMENT)
    Observable<Response<PutNewsCommentModel>> put(@Header("Authorization") String authorization, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);
}
