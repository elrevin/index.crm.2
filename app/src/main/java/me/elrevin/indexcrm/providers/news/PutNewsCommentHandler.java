package me.elrevin.indexcrm.providers.news;

import me.elrevin.indexcrm.mvp.model.PutNewsCommentModel;

public interface PutNewsCommentHandler {
    void onRequestFailure(Throwable t);
    void onPuted(PutNewsCommentModel result);
    void onAuthFailure();
}
