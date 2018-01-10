package me.elrevin.indexcrm.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elrevin.indexcrm.rest.RestClient;
import me.elrevin.indexcrm.rest.api.CheckAuthRequest;
import me.elrevin.indexcrm.rest.api.CheckCommonLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.CheckPersonalLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.CloseTaskRequest;
import me.elrevin.indexcrm.rest.api.GetClientsListRequest;
import me.elrevin.indexcrm.rest.api.GetNetRequest;
import me.elrevin.indexcrm.rest.api.GetNewsCommentsListRequest;
import me.elrevin.indexcrm.rest.api.GetNewstListRequest;
import me.elrevin.indexcrm.rest.api.GetPlushkiRequest;
import me.elrevin.indexcrm.rest.api.GetTasksListRequest;
import me.elrevin.indexcrm.rest.api.PutNewsCommentRequest;
import me.elrevin.indexcrm.rest.api.PutRateNewsCommentRequest;

@Module
public class RestModule {
    private RestClient restClient;


    public RestModule() {
        restClient = new RestClient();
    }


    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return restClient;
    }

    @Provides
    @Singleton
    public CheckCommonLoginAndPasswordRequest provideCheckLoginAndPasswordRequest() {
        return  restClient.createService(CheckCommonLoginAndPasswordRequest.class);
    }

    @Provides
    @Singleton
    public CheckPersonalLoginAndPasswordRequest provideCheckPersonalLoginAndPasswordRequest() {
        return restClient.createService(CheckPersonalLoginAndPasswordRequest.class);
    }

    @Provides
    @Singleton
    public CheckAuthRequest provideCheckAuthRequest() {
        return restClient.createService(CheckAuthRequest.class);
    }

    @Provides
    @Singleton
    public GetTasksListRequest provideGetTasksListRequest() {
        return restClient.createService(GetTasksListRequest.class);
    }

    @Provides
    @Singleton
    public GetClientsListRequest provideGetClientsListRequest() {
        return restClient.createService(GetClientsListRequest.class);
    }

    @Provides
    @Singleton
    public GetNewstListRequest provideGetNewstListRequest() {
        return restClient.createService(GetNewstListRequest.class);
    }

    @Provides
    @Singleton
    public GetNewsCommentsListRequest provideGetNewsCommentsListRequest() {
        return restClient.createService(GetNewsCommentsListRequest.class);
    }

    @Provides
    @Singleton
    public PutNewsCommentRequest providePutNewsCommentRequset() {
        return restClient.createService(PutNewsCommentRequest.class);
    }

    @Provides
    @Singleton
    public PutRateNewsCommentRequest providePutRateNewsCommentRequest() {
        return restClient.createService(PutRateNewsCommentRequest.class);
    }

    @Provides
    @Singleton
    public CloseTaskRequest provideCloseTaskRequest() {
        return restClient.createService(CloseTaskRequest.class);
    }

    @Provides
    @Singleton
    public GetNetRequest provideGetNetRequest() {
        return restClient.createService(GetNetRequest.class);
    }

    @Provides
    @Singleton
    public GetPlushkiRequest provideGetPlushkiRequest() {
        return restClient.createService(GetPlushkiRequest.class);
    }
}
