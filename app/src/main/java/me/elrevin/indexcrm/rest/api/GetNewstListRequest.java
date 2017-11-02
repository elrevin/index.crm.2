package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.NewstListModel;
import me.elrevin.indexcrm.rest.models.GetNewstListRequestModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface GetNewstListRequest {
    @GET(ApiMethods.GET_NEWS_LIST)
    Observable<Response<NewstListModel>> get(@Header("Authorization") String authorization, @QueryMap Map<String, String> map);
}
