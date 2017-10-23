package me.elrevin.indexcrm.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.elrevin.indexcrm.common.CurrentUser;
import me.elrevin.indexcrm.di.module.RestModule;
import me.elrevin.indexcrm.mvp.presenter.MainPresenter;
import me.elrevin.indexcrm.rest.models.BaseRequestModel;
import me.elrevin.indexcrm.ui.activity.BaseActivity;
import me.elrevin.indexcrm.ui.activity.LoginActivity;
import me.elrevin.indexcrm.di.module.ApplicationModule;
import me.elrevin.indexcrm.ui.fragment.AuthFirstFragment;
import me.elrevin.indexcrm.ui.fragment.AuthSecondFragment;
import me.elrevin.indexcrm.ui.fragment.BaseFragment;

@Singleton
@Component(
        modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {
    // Activities
    void inject(BaseActivity activity);
    void inject(LoginActivity activity);

    // Fragments
    void inject(BaseFragment fragment);
    void inject(AuthFirstFragment fragment);
    void inject(AuthSecondFragment fragment);

    // Presenters
    void inject(MainPresenter presenter);

    // Common
    void inject(CurrentUser currentUser);

    // models
    void inject(BaseRequestModel baseRequestModel);

}
