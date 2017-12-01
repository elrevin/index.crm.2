package me.elrevin.indexcrm.providers.news;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.NewsCommentModel;

public interface LoadCommentsListHandler {
    void onRequestFailure(Throwable t);
    void onLoaded(List<NewsCommentModel> list);
    void onAuthFailure();
}
