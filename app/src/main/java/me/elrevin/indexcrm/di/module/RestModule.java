package me.elrevin.indexcrm.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elrevin.indexcrm.rest.RestClient;
import me.elrevin.indexcrm.rest.api.CheckAuthRequest;
import me.elrevin.indexcrm.rest.api.CheckCommonLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.CheckPersonalLoginAndPasswordRequest;
import me.elrevin.indexcrm.rest.api.GetTasksListRequest;

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
}
