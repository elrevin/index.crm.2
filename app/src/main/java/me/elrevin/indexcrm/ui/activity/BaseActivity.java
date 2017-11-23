package me.elrevin.indexcrm.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
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

    public void onCloseByBackButton() {

    }

    public void onFragmentData(int type, Intent intent) {
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
