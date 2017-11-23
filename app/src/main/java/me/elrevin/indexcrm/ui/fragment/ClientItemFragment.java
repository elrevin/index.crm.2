package me.elrevin.indexcrm.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.adapters.PersonsRecyclerAdapter;
import me.elrevin.indexcrm.mvp.model.ClientModel;

public class ClientItemFragment extends BaseFragment {

    private ClientModel client;

    @BindView(R.id.tvClientName)
    TextView tvName;

    @BindView(R.id.llClientServices)
    LinearLayout llServices;

    @BindView(R.id.tvClientServices)
    TextView tvServices;

    @BindView(R.id.llPersons)
    LinearLayout llPersons;

    @BindView(R.id.rvPersons)
    RecyclerView rvPersons;

    LinearLayoutManager llmPersons;

    PersonsRecyclerAdapter personsRecyclerAdapter;

    public ClientItemFragment() {
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_client_item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName.setText(client.getName());
        if (client.getPersons() != null && client.getPersons().size() > 0) {
            llPersons.setVisibility(View.VISIBLE);
            personsRecyclerAdapter = new PersonsRecyclerAdapter(new ArrayList<>(client.getPersons()), (Context) getBaseActivity());
            rvPersons.setAdapter(personsRecyclerAdapter);
            llmPersons = new LinearLayoutManager(getBaseActivity());
            rvPersons.setLayoutManager(llmPersons);
        } else {
            llPersons.setVisibility(View.GONE);
        }

        if (client.getServices() == null || client.getServices().isEmpty()) {
            llServices.setVisibility(View.GONE);
        } else {
            ArrayList<String> services = new ArrayList<>();

            for (String si : client.getServices()) {
                switch (si) {
                    case "DEV":
                        services.add("Р");
                        break;
                    case "SUP":
                        services.add("С");
                        break;
                    case "SEO_POSITIONS":
                        services.add("ПП");
                        break;
                    case "SEO_TRAFFIC":
                        services.add("ПТ");
                        break;
                    case "SMM":
                        services.add("SMM");
                        break;
                    case "START":
                        services.add("IS");
                        break;
                    case "CONTEXT_MANAGEMENT":
                        services.add("КВ");
                        break;
                    case "CONTEXT_BUDGET":
                        services.add("КБ");
                        break;
                    default:
                        services.add("ХЗ");
                        break;
                }
            }

            this.tvServices.setText(TextUtils.join(", ", services));
            llServices.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public String onCreateToolbarTitle() {
        return client.getName();
    }

}
