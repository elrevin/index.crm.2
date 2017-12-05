package me.elrevin.indexcrm.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.PutNewsCommentModel;
import me.elrevin.indexcrm.mvp.view.NewsItemView;
import me.elrevin.indexcrm.providers.news.CommentsProvider;
import me.elrevin.indexcrm.providers.news.LoadCommentsListHandler;
import me.elrevin.indexcrm.providers.news.PutNewsCommentHandler;

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

    public void loadComments(boolean needScroll, String scrollToId) {
        LoadCommentsListHandler handler = new LoadCommentsListHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onLoaded(List<NewsCommentModel> list) {
                getViewState().onCommentsLoaded(list, needScroll, scrollToId);
            }

            @Override
            public void onAuthFailure() {

            }
        };
        commentsProvider.loadList(handler, newsItem.getId());
    }

    public void putComment(String text, NewsCommentModel replyTo) {
        PutNewsCommentHandler handler = new PutNewsCommentHandler() {
            @Override
            public void onRequestFailure(Throwable t) {

            }

            @Override
            public void onPuted(PutNewsCommentModel result) {
                getViewState().onPutNewsComment(result);
            }

            @Override
            public void onAuthFailure() {

            }
        };

        commentsProvider.put(handler, newsItem.getId(), text, (replyTo != null ? replyTo.getId() : "0"));
    }
}
