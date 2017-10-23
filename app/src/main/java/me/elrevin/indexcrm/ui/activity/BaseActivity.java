package me.elrevin.indexcrm.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.widget.FrameLayout;
import android.widget.Toast;

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

        if (haveToolbar()) {
            setContentView(R.layout.activity_base_w_toolbar);
        } else {
            setContentView(R.layout.activity_base_wo_toolbar);
        }

        CustomApp.getApplicationComponent().inject(this);

        FrameLayout parent = (FrameLayout) findViewById(R.id.wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);

        ButterKnife.bind(this);
    }


    @LayoutRes
    protected abstract int getMainContentLayout();


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
        removeCurrentFragment();
    }

    public void onFragmentData(int type, Intent intent) {
    }
}
