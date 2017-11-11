package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import butterknife.BindView;
import me.elrevin.indexcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsListFragment extends BaseFragment {

    @BindView(R.id.svSearchClients)
    SearchView searchView;

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
    }

    public boolean onSearchSubmitListener(String text) {
        Toast.makeText(getBaseActivity(), text, Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean onCloseSearchListener() {
        Toast.makeText(getBaseActivity(), "CLOSE", Toast.LENGTH_LONG).show();
        return false;
    }

    public boolean onSearchTextChangeListener(String text) {
        return false;
    }
}
