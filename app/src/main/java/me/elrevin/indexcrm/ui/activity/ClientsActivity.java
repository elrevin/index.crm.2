package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.presenter.ClientsPresenter;
import me.elrevin.indexcrm.mvp.view.ClientsView;
import me.elrevin.indexcrm.ui.fragment.ClientItemFragment;
import me.elrevin.indexcrm.ui.fragment.ClientsListFragment;

public class ClientsActivity extends BaseActivity implements ClientsView {

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    @InjectPresenter
    public ClientsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public void showClientsList() {
        setContent(new ClientsListFragment());
    }

    @Override
    public boolean setDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void setUpUi() {
        super.setUpUi();
        Intent intent = getIntent();
        if (intent.hasExtra("CLIENT_ITEM")) {
            ClientModel item = (ClientModel) intent.getSerializableExtra("CLIENT_ITEM");
            openClient(item);
        } else {
            presenter.showClientsList();
        }
    }

    @Override
    public void onLoginFail() {
        presenter.checkAuth();
    }

    public void openClient(ClientModel client) {
        ClientItemFragment fragment = new ClientItemFragment();
        fragment.setClient(client);
        addContent(fragment);
    }
}
