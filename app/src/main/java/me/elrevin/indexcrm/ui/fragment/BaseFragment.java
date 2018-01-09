package me.elrevin.indexcrm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import me.elrevin.indexcrm.ui.activity.BaseActivity;

public abstract class BaseFragment extends MvpAppCompatFragment {
    @LayoutRes
    protected abstract int getMainContentLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getMainContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public String createToolbarTitle() {
        return onCreateToolbarTitle();
    }

    public abstract String onCreateToolbarTitle();

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public boolean needFab() {
        return false;
    }

    public void login() {
        getBaseActivity().login();
    }

    public void onAuthFailure() {
        getBaseActivity().onLoginFail();
    }

    public void onRequestFailure(Throwable t) {
        getBaseActivity().onNetworkFailure(t);
    }

    public void onLoadingStart() {
        getBaseActivity().onLoadingStart();
    }

    public void onLoadingEnd() {
        getBaseActivity().onLoadingEnd();
    }
}
