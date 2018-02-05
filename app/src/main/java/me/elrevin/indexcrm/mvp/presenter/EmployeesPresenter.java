package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.view.EmployeesView;
import me.elrevin.indexcrm.providers.current_user.CheckAuthHandler;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;

@Inje
public class EmployeesPresenter extends MvpPresenter<EmployeesView> {
    @Inject
    CurrentUserProvider currentUserProvider;

    public EmployeesPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void checkAuth() {
        if (currentUserProvider.haveAuthData()) {
            currentUserProvider.checkAuth(new CheckAuthHandler() {
                @Override
                public void onRequestFailure(Throwable t) {
                    getViewState().onNetworkFailure(t);
                }

                @Override
                public void onAuthFailure() {
                    getViewState().login();
                }

                @Override
                public void onAuthCorrect() {
                    getViewState().afterLogin();
                }
            });
        } else {
            getViewState().login();
        }
    }
}
