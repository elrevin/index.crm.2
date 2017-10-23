package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.R;
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

    private void setUpUi() {
        setContent(new MainScreenFragment());
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_LOGIN);
    }

    @Override
    public void afterLogin() {
        setUpUi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_LOGIN) {
            if (resultCode == Constants.RESULT_OK) {
                setUpUi();
            } else {
                Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show();
            }
        }
    }
}
