package me.elrevin.indexcrm.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import me.elrevin.indexcrm.mvp.model.TasksListModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface GetTasksListRequest {
    @GET(ApiMethods.GET_TASKS_LIST)
    Observable<Response<TasksListModel>> get(@Header("Authorization") String authorization, @QueryMap Map<String, String> map);
}
