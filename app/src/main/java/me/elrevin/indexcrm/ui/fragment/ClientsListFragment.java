package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.elrevin.indexcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsListFragment extends Fragment {


    public ClientsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients_list, container, false);
    }

}
