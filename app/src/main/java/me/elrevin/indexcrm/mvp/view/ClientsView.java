package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.ClientModel;

public interface ClientsView extends MvpView {
    void showClientsList();
}
