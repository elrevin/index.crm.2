package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.view.MainView;
import me.elrevin.indexcrm.common.CurrentUser;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    CurrentUser currentUser;

    public MainPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void checkAuth() {
        if (currentUser.isAuth()) {
            // check login, password and token
        } else {
            getViewState().login();
        }
    }
}
