package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.adapters.ClientsRecyclerAdapter;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.presenter.ClientsListPresenter;
import me.elrevin.indexcrm.mvp.view.ClientsListView;

public class ClientsListFragment extends BaseFragment implements ClientsListView {

    @BindView(R.id.svSearchClients)
    SearchView searchView;

    @BindView(R.id.rvClientsList)
    RecyclerView rvClientsList;

    ClientsRecyclerAdapter clientsListAdapter;

    LinearLayoutManager rvClientsListLayoutManager;

    @InjectPresenter
    ClientsListPresenter presenter;

    public ClientsListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_clients_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients_list, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return onSearchSubmitListener(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return onSearchTextChangeListener(newText);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return onCloseSearchListener();
            }
        });

        clientsListAdapter = new ClientsRecyclerAdapter();
        rvClientsList.setAdapter(clientsListAdapter);
        rvClientsListLayoutManager = new LinearLayoutManager(getBaseActivity());
        rvClientsList.setLayoutManager(rvClientsListLayoutManager);

        presenter.loadClients();
    }

    public boolean onSearchSubmitListener(String text) {
        presenter.loadClients(text);
        return true;
    }

    public boolean onCloseSearchListener() {
        Toast.makeText(getBaseActivity(), "CLOSE", Toast.LENGTH_LONG).show();
        return false;
    }

    public boolean onSearchTextChangeListener(String text) {
        return false;
    }

    @Override
    public void onClientsLoaded(List<ClientModel> list) {
        clientsListAdapter.clearList();
        clientsListAdapter.addAll(list, true);
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Toast.makeText(getBaseActivity(), "Проблемы с сетью", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthFailure() {

    }
}
