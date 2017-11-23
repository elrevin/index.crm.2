package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.ClientModel;

public class ClientItemFragment extends BaseFragment {

    private ClientModel client;

    public ClientItemFragment() {
        // Required empty public constructor
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_item, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return null;
    }

}
