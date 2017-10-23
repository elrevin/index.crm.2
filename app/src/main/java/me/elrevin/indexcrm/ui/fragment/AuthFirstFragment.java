package me.elrevin.indexcrm.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.providers.current_user.CheckCommonLoginAndPasswordHandler;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.ui.activity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFirstFragment extends BaseFragment implements CheckCommonLoginAndPasswordHandler {

    @Inject
    public CurrentUserProvider currentUserProvider;

    @BindView(R.id.btnGo)
    Button btnGo;

    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApp.getApplicationComponent().inject(this);
    }

    public AuthFirstFragment() {
    }


    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth_first, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return "";
    }

    @OnClick(R.id.btnGo)
    public void btnGoClick(View v) {
        currentUserProvider.setLogin(etLogin.getText().toString());
        currentUserProvider.setPassword(etPassword.getText().toString());

        currentUserProvider.checkCommonLoginAndPassword(this);
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Log.e("index-crm", "Ошибка сети", t);
    }

    @Override
    public void onLoginOrPasswordIncorrect() {
        Toast.makeText(this.getActivity(), "Логин или пароль введены не верно", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginAndPasswordCorrect() {
        Intent intent = new Intent();
        intent.putExtra("step", 2);
        getBaseActivity().onFragmentData(BaseActivity.SET_AUTH_STEP, intent);
    }
}
