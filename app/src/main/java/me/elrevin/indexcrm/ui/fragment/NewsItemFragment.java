package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.adapters.NewsCommentsRecyclerAdapter;
import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.presenter.NewsItemPresenter;
import me.elrevin.indexcrm.mvp.view.NewsItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsItemFragment extends BaseFragment implements NewsItemView {

    private NewsModel newsItem;

    @InjectPresenter
    NewsItemPresenter presenter;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvAuthor)
    TextView tvAuthor;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvText)
    TextView tvText;

    @BindView(R.id.rvComments)
    RecyclerView rvComments;

    LinearLayoutManager llmComments;

    NewsCommentsRecyclerAdapter newsCommentsRecyclerAdapter;

    public NewsItemFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_item, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return newsItem.getName();
    }

    public void setNewsItem(NewsModel newsItem) {
        this.newsItem = newsItem;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (newsItem != null) {
            presenter.setNewsItem(newsItem);
            presenter.showNews();

            newsCommentsRecyclerAdapter = new NewsCommentsRecyclerAdapter();
            rvComments.setAdapter(newsCommentsRecyclerAdapter);
            llmComments = new LinearLayoutManager(getBaseActivity());
            rvComments.setLayoutManager(llmComments);

            presenter.loadComments();
        }
    }

    @Override
    public void onShowNews(NewsModel item) {
        newsItem = item;
        tvDate.setText(item.getDate());
        tvAuthor.setText(item.getAutor());
        tvTitle.setText(item.getName());
        tvText.setText(item.getText());
    }

    @Override
    public void onCommentsLoaded(List<NewsCommentModel> list) {
        newsCommentsRecyclerAdapter.clearList();
        newsCommentsRecyclerAdapter.addAll(list, true);
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Toast.makeText(getBaseActivity(), "Проблемы с сетью", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthFailure() {

    }
}
