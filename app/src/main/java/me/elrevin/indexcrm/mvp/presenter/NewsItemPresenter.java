package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.view.NewsItemView;
import me.elrevin.indexcrm.providers.news.CommentsProvider;
import me.elrevin.indexcrm.providers.news.LoadCommentsListHandler;

@InjectViewState
public class NewsItemPresenter extends MvpPresenter<NewsItemView> {
    @Inject
    CommentsProvider commentsProvider;

    NewsModel newsItem;

    public NewsItemPresenter() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void setNewsItem(NewsModel newsItem) {
        this.newsItem = newsItem;
    }

    public void showNews() {
        getViewState().onShowNews(newsItem);
    }

    public void loadComments() {
        LoadCommentsListHandler handler = new LoadCommentsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onLoaded(List<NewsCommentModel> list) {
                getViewState().onCommentsLoaded(list);
            }

            @Override
            public void onAuthFailure() {

            }
        };
        commentsProvider.loadList(handler, newsItem.getId());
    }
}
