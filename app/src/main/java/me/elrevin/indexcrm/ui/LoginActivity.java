package me.elrevin.indexcrm.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.elrevin.indexcrm.Constants;
import me.elrevin.indexcrm.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnOkClick(View v) {
        setResult(Constants.RESULT_OK);
        finish();
    }

    public void btnCancelClick(View v) {
        setResult(Constants.RESULT_CANCEL);
        finish();
    }
}
