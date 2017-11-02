package me.elrevin.indexcrm.providers.news;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.NewsModel;

public interface LoadNewsListHandler {
    void onRequestFailure(Throwable t);
    void onLoaded(List<NewsModel> list);
    void onAuthFailure();
}
