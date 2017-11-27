package me.elrevin.indexcrm.ui.activity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.FragmentManager;
import me.elrevin.indexcrm.ui.fragment.BaseFragment;

abstract public class BaseActivity extends MvpAppCompatActivity {

    public static final int SET_AUTH_STEP = 1;
    public static final int AUTH_SUCCESSFUL = 2;

    public static final int REQUEST_PHONE_CALL = 1;

    protected String callPhoneNumber;

    @Inject
    public FragmentManager fragmentManager;

    protected boolean haveToolbar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getMainContentLayout() == 0) {
            if (haveToolbar()) {
                setContentView(R.layout.activity_base_w_toolbar);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                if (setDisplayHomeAsUpEnabled()) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
            } else {
                setContentView(R.layout.activity_base_wo_toolbar);
            }
        }

        CustomApp.getApplicationComponent().inject(this);

        ButterKnife.bind(this);
    }


    @LayoutRes
    protected abstract int getMainContentLayout();

    public boolean setDisplayHomeAsUpEnabled() {
        return false;
    }

    public void fragmentOnScreen(BaseFragment baseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle());
    }


    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setContent(BaseFragment fragment) {
        fragmentManager.setFragment(this, fragment, R.id.wrapper);
    }

    public void addContent(BaseFragment fragment) {
        fragmentManager.addFragment(this, fragment, R.id.wrapper);
    }

    public boolean removeCurrentFragment() {
        return fragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment) {
        return fragmentManager.removeFragment(this, fragment);
    }


    @Override
    public void onBackPressed() {
        if (!removeCurrentFragment()) {
            onCloseByBackButton();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onCloseByBackButton() {

    }

    public void onFragmentData(int type, Intent intent) {
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void doPhoneCall(boolean request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (request) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_PHONE_CALL);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callPhoneNumber));
            this.startActivity(intent);
        }
    }

    public void phoneCall(String num) {
        callPhoneNumber = num;
        doPhoneCall(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doPhoneCall(false);
                }
            }
        }
    }
}
