package me.elrevin.indexcrm.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.elrevin.indexcrm.mvp.presenter.ClientsListPresenter;
import me.elrevin.indexcrm.mvp.presenter.ClientsPresenter;
import me.elrevin.indexcrm.mvp.presenter.MainScreenPresenter;
import me.elrevin.indexcrm.mvp.presenter.NewsItemPresenter;
import me.elrevin.indexcrm.mvp.presenter.TasksListPresenter;
import me.elrevin.indexcrm.mvp.presenter.TasksPresenter;
import me.elrevin.indexcrm.providers.clients.ClientsProvider;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.di.module.RestModule;
import me.elrevin.indexcrm.mvp.presenter.MainPresenter;
import me.elrevin.indexcrm.providers.news.CommentsProvider;
import me.elrevin.indexcrm.providers.news.NewsProvider;
import me.elrevin.indexcrm.providers.tasks.TasksProvider;
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
    void inject(MainScreenPresenter presenter);
    void inject(ClientsPresenter presenter);
    void inject(ClientsListPresenter presenter);
    void inject(NewsItemPresenter presenter);
    void inject(TasksPresenter presenter);
    void inject(TasksListPresenter presenter);

    // Providers
    void inject(CurrentUserProvider provider);
    void inject(TasksProvider provider);
    void inject(ClientsProvider provider);
    void inject(NewsProvider provider);
    void inject(CommentsProvider provider);

    // models
    void inject(BaseRequestModel model);

}
