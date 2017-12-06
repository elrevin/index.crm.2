package me.elrevin.indexcrm.providers.news;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.elrevin.indexcrm.CustomApp;
import me.elrevin.indexcrm.providers.current_user.CurrentUserProvider;
import me.elrevin.indexcrm.rest.api.ApiMethods;
import me.elrevin.indexcrm.rest.api.GetNewsCommentsListRequest;
import me.elrevin.indexcrm.rest.api.PutNewsCommentRequest;
import me.elrevin.indexcrm.rest.api.PutRateNewsCommentRequest;
import me.elrevin.indexcrm.rest.models.GetNewsCommentsListRequestModel;
import me.elrevin.indexcrm.rest.models.PutNewsCommentRequestDataModel;
import me.elrevin.indexcrm.rest.models.PutNewsCommentRequestModel;
import me.elrevin.indexcrm.rest.models.PutRateNewsCommentRequestDataModel;
import me.elrevin.indexcrm.rest.models.PutRateNewsCommentRequestModel;

public class CommentsProvider {
    @Inject
    CurrentUserProvider currentUserProvider;

    @Inject
    GetNewsCommentsListRequest getNewsCommentsListRequest;

    @Inject
    PutNewsCommentRequest putNewsCommentRequest;

    @Inject
    PutRateNewsCommentRequest putRateNewsCommentRequest;

    public CommentsProvider() {
        CustomApp.getApplicationComponent().inject(this);
    }

    public void loadList(LoadCommentsListHandler handler, String newsId) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        getNewsCommentsListRequest.get(auth, new GetNewsCommentsListRequestModel(newsId).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                handler.onLoaded(response.body().getList());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }

                    if (response.code() == 401) {
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. News.loadList"));
                });
    }

    public void putComment(PutNewsCommentHandler handler, String newsId, String text, String replyToId) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        putNewsCommentRequest.put(auth, new PutNewsCommentRequestModel().toMap(), new PutNewsCommentRequestDataModel(newsId, text, replyToId).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                handler.onPuted(response.body());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }

                    if (response.code() == 401) {
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. News.loadList"));
                });
    }

    public void putRateComment(PutRateNewsCommentHandler handler, String newsId, String commentId, String sign) {
        String auth = ApiMethods.getBasicAuthString(currentUserProvider.getLogin(), currentUserProvider.getPassword());
        putRateNewsCommentRequest.put(auth, new PutRateNewsCommentRequestModel().toMap(), new PutRateNewsCommentRequestDataModel(newsId, commentId, sign).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    handler.onRequestFailure(t);
                    return null;
                })
                .subscribe(response -> {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                handler.onPuted(response.body());
                                return;
                            } else if (response.body().getError().equals("AUTH_ERROR")) {
                                handler.onAuthFailure();
                                return;
                            }
                        }
                    }

                    if (response.code() == 401) {
                        handler.onAuthFailure();
                        return;
                    }
                    handler.onRequestFailure(new Throwable("Проблемы с сетью. News.loadList"));
                });
    }


}
