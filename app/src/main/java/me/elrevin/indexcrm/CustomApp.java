package me.elrevin.indexcrm;

import android.app.Application;

import me.elrevin.indexcrm.di.component.ApplicationComponent;
import me.elrevin.indexcrm.di.component.DaggerApplicationComponent;
import me.elrevin.indexcrm.di.module.ApplicationModule;

public class CustomApp extends Application {
    private static ApplicationComponent applicationComponent;
    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
