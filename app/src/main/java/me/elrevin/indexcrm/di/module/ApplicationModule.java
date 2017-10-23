package me.elrevin.indexcrm.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.common.FragmentManager;

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
    public FragmentManager provideFragmentManager(){
        return new FragmentManager();
    }
}
