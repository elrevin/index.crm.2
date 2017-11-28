package me.elrevin.indexcrm.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.CustomListView;
import me.elrevin.indexcrm.common.adapters.ClientsListAdapter;
import me.elrevin.indexcrm.common.adapters.NewsListAdapter;
import me.elrevin.indexcrm.common.adapters.TasksListAdapter;
import me.elrevin.indexcrm.mvp.model.ClientModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.MainScreenPresenter;
import me.elrevin.indexcrm.mvp.view.MainScreenView;
import me.elrevin.indexcrm.ui.activity.ClientsActivity;
import me.elrevin.indexcrm.ui.activity.MainActivity;

public class MainScreenFragment extends BaseFragment implements MainScreenView {

    @BindView(R.id.lvTasks)
    CustomListView lvTasks;

    @BindView(R.id.lvClients)
    CustomListView lvClients;

    @BindView(R.id.lvNews)
    CustomListView lvNews;

    @BindView(R.id.btnAllClients)
    Button btnAllClients;

    TasksListAdapter tasksListAdapter;
    ArrayList<TaskModel> tasksList;

    ClientsListAdapter clientsListAdapter;
    ArrayList<ClientModel> clientsList;

    NewsListAdapter newsListAdapter;
    ArrayList<NewsModel> newsList;

    @InjectPresenter
    MainScreenPresenter presenter;

    public MainScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasksList = new ArrayList<>();
        tasksListAdapter = new TasksListAdapter(tasksList, getBaseActivity());
        lvTasks.setAdapter(tasksListAdapter);
        presenter.loadTasks();

        clientsList = new ArrayList<>();
        clientsListAdapter = new ClientsListAdapter(clientsList, getBaseActivity());
        lvClients.setAdapter(clientsListAdapter);
        presenter.loadClients();
        lvClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openClientItem((ClientModel) clientsListAdapter.getItem(position));
            }
        });

        newsList = new ArrayList<>();
        newsListAdapter = new NewsListAdapter(newsList, getBaseActivity());
        lvNews.setAdapter(newsListAdapter);
        presenter.loadNews();
    }

    @Override
    public String onCreateToolbarTitle() {
        return "index.crm";
    }


    @Override
    public void onTasksLoaded(List<TaskModel> list) {
        tasksList.clear();
        tasksList.addAll(list);
        tasksListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClientsLoaded(List<ClientModel> list) {
        clientsList.clear();
        clientsList.addAll(list);
        clientsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewsLoaded(List<NewsModel> list) {
        newsList.clear();
        newsList.addAll(list);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Toast.makeText(getBaseActivity(), "Проблемы с сетью", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthFailure() {
        ((MainActivity) getBaseActivity()).login();
    }

    @OnClick(R.id.btnAllClients)
    public void onBtnAllClientsClick(View v) {
        presenter.openClientsScreen();
    }

    @Override
    public void openClientsScreen() {
        getBaseActivity().startActivity(ClientsActivity.class);
    }

    public void openClientItem(ClientModel item) {
        Intent intent = new Intent(getBaseActivity(), ClientsActivity.class);
        intent.putExtra("CLIENT_ITEM", item);
        startActivity(intent);
    }
}
