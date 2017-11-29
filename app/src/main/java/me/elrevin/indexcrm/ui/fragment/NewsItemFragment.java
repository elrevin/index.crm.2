package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.NewsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsItemFragment extends BaseFragment {

    private NewsModel newsItem;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvAuthor)
    TextView tvAuthor;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvText)
    TextView tvText;

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
            tvDate.setText(newsItem.getDate());
            tvAuthor.setText(newsItem.getAutor());
            tvTitle.setText(newsItem.getName());
            tvText.setText(newsItem.getText());
        }
    }
}
