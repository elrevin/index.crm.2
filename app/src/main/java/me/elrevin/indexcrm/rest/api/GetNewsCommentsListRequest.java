package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.NewsCommentsListModel;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface GetNewsCommentsListRequest {
    Observable<Response<NewsCommentsListModel>> get(@Header("Authorization") String authorization, @QueryMap Map<String, String> map);
}
