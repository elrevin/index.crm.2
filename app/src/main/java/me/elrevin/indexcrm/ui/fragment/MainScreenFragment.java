package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.elrevin.indexcrm.R;

public class MainScreenFragment extends BaseFragment {


    public MainScreenFragment() {
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_main_screen;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return null;
    }

}
