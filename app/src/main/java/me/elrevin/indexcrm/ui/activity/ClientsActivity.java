package me.elrevin.indexcrm.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.view.ClientsView;

public class ClientsActivity extends BaseActivity implements ClientsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public void showClientsList() {

    }
}
