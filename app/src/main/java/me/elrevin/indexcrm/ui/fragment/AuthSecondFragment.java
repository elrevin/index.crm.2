package me.elrevin.indexcrm.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import me.elrevin.indexcrm.providers.current_user.CheckPersonalLoginAndPasswordHandler;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.ui.activity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthSecondFragment extends BaseFragment {

    @Inject
    public CurrentUserProvider currentUserProvider;

    @BindView(R.id.btnEnter)
    Button btnEnter;

    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etPassword)
    EditText etPassword;


    public AuthSecondFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApp.getApplicationComponent().inject(this);
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_second, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return null;
    }

    @OnClick(R.id.btnEnter)
    public void onBtnEnterClick(View view) {
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();
        getBaseActivity().onLoadingStart();
        currentUserProvider.checkPersonalLoginAndPassword(login, password, new CheckPersonalLoginAndPasswordHandler() {
            @Override
            public void onRequestFailure(Throwable t) {
                getBaseActivity().onLoadingEnd();
                Toast.makeText(getBaseActivity(), "Ошибка сети", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoginOrPasswordIncorrect() {
                getBaseActivity().onLoadingEnd();
                Toast.makeText(getBaseActivity(), "Логин или пароль введены не верно", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoginAndPasswordCorrect() {
                getBaseActivity().onLoadingEnd();
                getBaseActivity().onFragmentData(BaseActivity.AUTH_SUCCESSFUL, null);
            }

            @Override
            public void onCommonAuthFailure() {
                getBaseActivity().onLoadingEnd();
                Intent intent = new Intent();
                intent.putExtra("step", 1);
                getBaseActivity().onFragmentData(BaseActivity.SET_AUTH_STEP, intent);
            }
        });
    }

}
