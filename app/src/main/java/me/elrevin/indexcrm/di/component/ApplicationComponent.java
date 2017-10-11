package me.elrevin.indexcrm.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.elrevin.indexcrm.mvp.presenter.MainPresenter;
import me.elrevin.indexcrm.ui.BaseActivity;
import me.elrevin.indexcrm.ui.MainActivity;
import me.elrevin.indexcrm.di.module.ApplicationModule;

@Singleton
@Component(
        modules = {ApplicationModule.class})
public interface ApplicationComponent {
    // Activities
    void inject(BaseActivity activity);
//
    // Presenters
    void inject(MainPresenter presenter);
}
