package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.presenter.EmployeesPresenter;

public class EmployeesActivity extends BaseActivity  {

    @InjectPresenter
    public EmployeesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_employees;
    }

    @Override
    public void onLoginFail() {
        presenter.checkAuth();
    }


}
