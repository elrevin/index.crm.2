package me.elrevin.indexcrm.ui;
import android.os.Bundle;
import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.common.CurrentUser;

abstract public class BaseActivity extends MvpAppCompatActivity {

    @Inject
    CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApp.getApplicationComponent().inject(this);
    }
}
