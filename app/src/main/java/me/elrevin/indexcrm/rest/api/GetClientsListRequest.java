package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.ClientsListModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface GetClientsListRequest {
    @GET(ApiMethods.GET_CLIENTS_LIST)
    Observable<Response<ClientsListModel>> get(@Header("Authorization") String authorization, @QueryMap Map<String, String> map);
}
