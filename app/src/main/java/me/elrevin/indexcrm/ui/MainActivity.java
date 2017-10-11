package me.elrevin.indexcrm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.presenter.MainPresenter;
import me.elrevin.indexcrm.mvp.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainPresenter.checkAuth();
    }

    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_LOGIN) {
            if (resultCode == Constants.RESULT_OK) {
                Toast.makeText(this, "Login Ok", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
