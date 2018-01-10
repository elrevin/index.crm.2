package me.elrevin.indexcrm.ui.activity;
import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.CustomActivityManager;
import me.elrevin.indexcrm.common.FragmentManager;
import me.elrevin.indexcrm.ui.fragment.BaseFragment;

abstract public class BaseActivity extends MvpAppCompatActivity {

    public static final int SET_AUTH_STEP = 1;
    public static final int AUTH_SUCCESSFUL = 2;

    public static final int REQUEST_PHONE_CALL = 1;

    protected int loadingStarts;

    private int mShortAnimationDuration;

    protected String callPhoneNumber;

    @BindView(R.id.llContent)
    LinearLayout llContent;

    @BindView(R.id.pbSpinner)
    ProgressBar pbSpinner;

    @Inject
    public FragmentManager fragmentManager;

    @Inject
    public CustomActivityManager activityManager;

    protected boolean isUiSettedUp = false;

    protected boolean haveToolbar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadingStarts = 0;

        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

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
        finish();
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

    protected void setUpUi() {
        isUiSettedUp = true;
        pbSpinner.setVisibility(View.GONE);
        llContent.setVisibility(View.VISIBLE);
    }

    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_LOGIN);
    }

    public void afterLogin() {
        if (!isUiSettedUp) {
            setUpUi();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_LOGIN) {
            if (resultCode == Constants.RESULT_OK) {
                setUpUi();
            } else {
                onLoginFail();
            }
        }
    }

    public abstract void onLoginFail();

    public void onNetworkFailure(Throwable t) {
        onLoadingEnd();
        Log.e("index.crm", t.toString());
        Toast.makeText(this, "Нет сети", Toast.LENGTH_LONG).show();
    }

    public void onLoadingStart() {
        if (loadingStarts == 0) {
            pbSpinner.setAlpha(0f);
            pbSpinner.setVisibility(View.VISIBLE);
            pbSpinner.animate()
                    .alpha(1f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(null);

            llContent.animate()
                    .alpha(0f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            llContent.setVisibility(View.GONE);
                        }
                    });
        }
        loadingStarts++;
    }

    public void onLoadingEnd() {
        if (loadingStarts == 1) {
            llContent.setAlpha(0f);
            llContent.setVisibility(View.VISIBLE);

            pbSpinner.animate()
                    .alpha(0f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            pbSpinner.setVisibility(View.GONE);
                        }
                    });
            llContent.animate()
                    .alpha(1f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(null);
        }
        if (loadingStarts > 0) {
            loadingStarts--;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityManager.setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearReferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearReferences();
    }

    private void clearReferences(){
        BaseActivity currActivity = activityManager.getCurrentActivity();
        if (this.equals(currActivity))
            activityManager.setCurrentActivity(null);
    }

    public void onHomeNet() {
//        if (haveToolbar()) {
//            ((ImageView) findViewById(R.id.ivHomeNet)).setVisibility(View.VISIBLE);
//        }
    }

    public void onResumeHomeNet() {
//        if (haveToolbar()) {
//            ((ImageView) findViewById(R.id.ivHomeNet)).setVisibility(View.GONE);
//        }
    }
}
