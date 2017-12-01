package me.elrevin.indexcrm.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.ui.fragment.NewsItemFragment;

public class NewsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("NEWS_ITEM")) {
            NewsModel item = (NewsModel) intent.getSerializableExtra("NEWS_ITEM");
            openNews(item);
        } else {
            finish();
        }
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
}
