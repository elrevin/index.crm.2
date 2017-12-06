package me.elrevin.indexcrm.providers.news;

import me.elrevin.indexcrm.mvp.model.PutRateNewsCommentModel;

public interface PutRateNewsCommentHandler {
    void onRequestFailure(Throwable t);
    void onPuted(PutRateNewsCommentModel result);
    void onAuthFailure();
}
