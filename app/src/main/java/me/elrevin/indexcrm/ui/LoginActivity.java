package me.elrevin.indexcrm.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.btnGo)
    Button btnGo;

    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGo)
    public void btnGoClick(View v) {
        Toast.makeText(this, etLogin.getText().toString(), Toast.LENGTH_LONG).show();
        setResult(Constants.RESULT_OK);
        finish();
    }
}
