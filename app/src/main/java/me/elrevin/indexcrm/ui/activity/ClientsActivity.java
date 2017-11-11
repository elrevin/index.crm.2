package me.elrevin.indexcrm.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.mvp.presenter.ClientsPresenter;
import me.elrevin.indexcrm.mvp.view.ClientsView;
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
        presenter.showClientsList();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public void showClientsList() {
        setContent(new ClientsListFragment());
    }
}
