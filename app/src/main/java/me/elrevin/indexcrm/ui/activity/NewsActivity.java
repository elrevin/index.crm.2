package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.presenter.NewsPresenter;
import me.elrevin.indexcrm.mvp.view.NewsView;
import me.elrevin.indexcrm.ui.fragment.NewsItemFragment;

public class NewsActivity extends BaseActivity implements NewsView {

    @InjectPresenter
    NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return 0;
    }

    protected void openNews(NewsModel item) {
        NewsItemFragment fragment = new NewsItemFragment();
        fragment.setNewsItem(item);
        setContent(fragment);
    }

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    @Override
    public boolean setDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void setUpUi() {
        super.setUpUi();
        Intent intent = getIntent();
        if (intent.hasExtra("NEWS_ITEM")) {
            NewsModel item = (NewsModel) intent.getSerializableExtra("NEWS_ITEM");
            openNews(item);
        } else {
            finish();
        }
    }

    @Override
    public void onLoginFail() {
        presenter.checkAuth();
    }

}
