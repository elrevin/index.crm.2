package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.ui.fragment.AuthFirstFragment;
import me.elrevin.indexcrm.ui.fragment.AuthSecondFragment;

public class LoginActivity extends BaseActivity {

    @Inject
    public CurrentUserProvider currentUserProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApp.getApplicationComponent().inject(this);
        setUpUi();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    public void onFragmentData(int type, Intent intent) {
        if (type == BaseActivity.SET_AUTH_STEP) {
            if (intent.hasExtra("step")) {
                if (intent.getIntExtra("step", 0) == 2) {
                    addContent(new AuthSecondFragment());
                } else if (intent.getIntExtra("step", 0) == 1) {
                    removeCurrentFragment();
                }
            }
        }
        if (type == BaseActivity.AUTH_SUCCESSFUL) {
            setResult(Constants.RESULT_OK, null);
            finish();
        }
    }

    @Override
    protected void setUpUi() {
        super.setUpUi();
        setContent(new AuthFirstFragment());
    }

    @Override
    public void onLoginFail() {
        // We don't use this method, but it must be.
    }

}
