package me.elrevin.indexcrm.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.elrevin.indexcrm.MainActivity;
import me.elrevin.indexcrm.di.module.ApplicationModule;

@Singleton
@Component(
        modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
