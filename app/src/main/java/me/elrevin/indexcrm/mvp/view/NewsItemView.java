package me.elrevin.indexcrm.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.PutNewsCommentModel;
import me.elrevin.indexcrm.mvp.model.PutRateNewsCommentModel;

public interface NewsItemView extends MvpView {
    void onShowNews(NewsModel item);
    void onCommentsLoaded(List<NewsCommentModel> list, boolean needScroll, String scrollToId);
    void onRequestFailure(Throwable t);
    void onAuthFailure();
    void onPutNewsComment(PutNewsCommentModel result);
    void onPutRateNewsComment(PutRateNewsCommentModel result);
}
