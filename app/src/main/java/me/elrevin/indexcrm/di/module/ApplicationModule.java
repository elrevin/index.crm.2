package me.elrevin.indexcrm.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CustomActivityManager;
import me.elrevin.indexcrm.providers.clients.ClientsProvider;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.common.FragmentManager;
import me.elrevin.indexcrm.providers.news.CommentsProvider;
import me.elrevin.indexcrm.providers.news.NewsProvider;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;

@Module
public class ApplicationModule {
    private CustomApp app;

    public ApplicationModule(CustomApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    public CurrentUserProvider provideCurrentUser() {
        return new CurrentUserProvider(app);
    }

    @Provides
    @Singleton
    public TasksProvider provideTasksProvider() {
        return new TasksProvider();
    }

    @Provides
    @Singleton
    public ClientsProvider provideClientsProvider() {
        return new ClientsProvider();
    }

    @Provides
    @Singleton
    public NewsProvider provideNewsProvider() {
        return new NewsProvider();
    }

    @Provides
    @Singleton
    public CommentsProvider prevideCommentsProvider() {
        return new CommentsProvider();
    }

    @Provides
    public FragmentManager provideFragmentManager(){
        return new FragmentManager();
    }

    @Provides
    @Singleton
    public CustomActivityManager provideActivityManager() {
        return new CustomActivityManager();
    }
}
