package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.adapters.OnRecyclerViewItemClickHandler;
import me.elrevin.indexcrm.common.adapters.TasksListRecyclerAdapter;
import me.elrevin.indexcrm.mvp.model.TaskModel;
import me.elrevin.indexcrm.mvp.presenter.TasksListPresenter;
import me.elrevin.indexcrm.mvp.view.TasksListView;
import me.elrevin.indexcrm.ui.activity.TasksActivity;


public class TasksListFragment extends BaseFragment implements TasksListView {
    @BindView(R.id.rvTasksList)
    RecyclerView rvTasksList;

    LinearLayoutManager layoutManager;
    TasksListRecyclerAdapter listAdapter;

    @InjectPresenter
    TasksListPresenter presenter;

    public TasksListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_tasks_list;
    }

    @Override
    public String onCreateToolbarTitle() {
        return "Задачи";
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdapter = new TasksListRecyclerAdapter(getBaseActivity());
        rvTasksList.setAdapter(listAdapter);
        layoutManager = new LinearLayoutManager(getBaseActivity());
        rvTasksList.setLayoutManager(layoutManager);

        listAdapter.setOnItemClickHandler((item, index) -> openTask((TaskModel) item));

        presenter.loadTasks();
    }

    @Override
    public void onTasksLoaded(List<TaskModel> list) {
        listAdapter.clearList();
        listAdapter.addAll(list, true);
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Toast.makeText(getBaseActivity(), "Проблемы с сетью", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthFailure() {

    }

    @Override
    public void openTask(TaskModel task) {
        ((TasksActivity)getBaseActivity()).openTask(task);
    }
}
