package me.elrevin.indexcrm.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import me.elrevin.indexcrm.ui.activity.NewsActivity;
import me.elrevin.indexcrm.ui.activity.TasksActivity;

public class MainScreenFragment extends BaseFragment implements MainScreenView {

    @BindView(R.id.lvTasks)
    CustomListView lvTasks;

    @BindView(R.id.lvClients)
    CustomListView lvClients;

    @BindView(R.id.lvNews)
    CustomListView lvNews;

    @BindView(R.id.btnAllClients)
    Button btnAllClients;

    @BindView(R.id.btnAllTasks)
    Button btnAllTasks;

    @BindView(R.id.ivHomeNet)
    ImageView ivHomeNet;

    @BindView(R.id.tvPlushki)
    TextView tvPlushki;

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
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openTaskItem((TaskModel) tasksListAdapter.getItem(position));
            }
        });

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
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNewsItem((NewsModel) newsListAdapter.getItem(position));
            }
        });
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

    @OnClick(R.id.btnAllTasks)
    public void onBtnAllTasksClick(View v) {
        presenter.openTasksScreen();
    }

    @Override
    public void openClientsScreen() {
        getBaseActivity().startActivity(ClientsActivity.class);
    }

    @Override
    public void openTasksScreen() {
        getBaseActivity().startActivity(TasksActivity.class);
    }

    @Override
    public void onGetNet(boolean home) {
        ivHomeNet.setVisibility(home ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onGetPlushki(Integer count) {
        tvPlushki.setText(count.toString());
    }

    public void openClientItem(ClientModel item) {
        Intent intent = new Intent(getBaseActivity(), ClientsActivity.class);
        intent.putExtra("CLIENT_ITEM", item);
        startActivity(intent);
    }

    public void openNewsItem(NewsModel item) {
        Intent intent = new Intent(getBaseActivity(), NewsActivity.class);
        intent.putExtra("NEWS_ITEM", item);
        startActivity(intent);
    }

    public void openTaskItem(TaskModel item) {
        Intent intent = new Intent(getBaseActivity(), TasksActivity.class);
        intent.putExtra("TASK_ITEM", item);
        startActivityForResult(intent, 9);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9 && resultCode == 1) {
            if (!data.getStringExtra("id").isEmpty()) {
                TaskModel item;
                for (int i = 0; i < tasksList.size(); i++) {
                    item = tasksList.get(i);
                    if (item.getId().equals(data.getStringExtra("id"))) {
                        tasksList.remove(i);
                    }
                }
                tasksListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getNet();
        presenter.getPlushki();
    }
}
