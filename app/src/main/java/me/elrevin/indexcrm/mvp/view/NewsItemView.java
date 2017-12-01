package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;

public interface NewsItemView extends MvpView {
    void onShowNews(NewsModel item);
    void onCommentsLoaded(List<NewsCommentModel> list);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
}
