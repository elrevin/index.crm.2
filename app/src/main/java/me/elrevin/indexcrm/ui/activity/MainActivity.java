package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.mvp.presenter.MainPresenter;
import me.elrevin.indexcrm.mvp.view.MainView;
import me.elrevin.indexcrm.ui.fragment.MainScreenFragment;

public class MainActivity extends BaseActivity implements MainView {

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    @InjectPresenter
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainPresenter.checkAuth();
    }

    protected void setUpUi() {
        super.setUpUi();
        setContent(new MainScreenFragment());
    }

    @Override
    public void onLoginFail() {
        mainPresenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }
}
