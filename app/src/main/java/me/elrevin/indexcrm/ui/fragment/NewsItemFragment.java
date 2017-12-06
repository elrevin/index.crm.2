package me.elrevin.indexcrm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.common.adapters.NewsCommentsRecyclerAdapter;
import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.mvp.model.NewsModel;
import me.elrevin.indexcrm.mvp.model.PutNewsCommentModel;
import me.elrevin.indexcrm.mvp.model.PutRateNewsCommentModel;
import me.elrevin.indexcrm.mvp.presenter.NewsItemPresenter;
import me.elrevin.indexcrm.mvp.view.NewsItemView;

public class NewsItemFragment extends BaseFragment implements NewsItemView {

    private NewsModel newsItem;

    @InjectPresenter
    NewsItemPresenter presenter;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvAuthor)
    TextView tvAuthor;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvText)
    TextView tvText;

    @BindView(R.id.rvComments)
    RecyclerView rvComments;

    @BindView(R.id.btnSend)
    ImageButton btnSend;

    @BindView(R.id.edtComment)
    EditText edtComment;

    @BindView(R.id.rlReplyContainer)
    RelativeLayout rlReplyContainer;

    @BindView(R.id.tvReplyText)
    TextView tvReplyText;

    @BindView(R.id.ibCancelReply)
    ImageButton ibCancelReply;

    LinearLayoutManager llmComments;

    NewsCommentsRecyclerAdapter newsCommentsRecyclerAdapter;

    NewsCommentModel replyTo = null;

    public NewsItemFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_item, container, false);
    }

    @Override
    public String onCreateToolbarTitle() {
        return newsItem.getName();
    }

    public void setNewsItem(NewsModel newsItem) {
        this.newsItem = newsItem;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (newsItem != null) {
            presenter.setNewsItem(newsItem);
            presenter.showNews();
        }
    }

    @Override
    public void onShowNews(NewsModel item) {
        newsItem = item;
        tvDate.setText(item.getDate());
        tvAuthor.setText(item.getAutor());
        tvTitle.setText(item.getName());
        tvText.setText(item.getText());

        newsCommentsRecyclerAdapter = new NewsCommentsRecyclerAdapter(this);
        rvComments.setAdapter(newsCommentsRecyclerAdapter);
        llmComments = new LinearLayoutManager(getBaseActivity());
        rvComments.setLayoutManager(llmComments);

        presenter.loadComments(false, null);
    }

    @Override
    public void onCommentsLoaded(List<NewsCommentModel> list, boolean needScroll, String scrollToId) {
        int pos;
        rlReplyContainer.setVisibility(View.GONE);
        tvReplyText.setText("");
        replyTo = null;
        newsCommentsRecyclerAdapter.clearList();
        newsCommentsRecyclerAdapter.addAll(list, true);
        if (needScroll) {
            if (scrollToId != null) {
                pos = newsCommentsRecyclerAdapter.getPosition(scrollToId);
            } else {
                pos = newsCommentsRecyclerAdapter.getItemCount()-1;
            }
            rvComments.scrollToPosition(pos);
        }
    }

    @Override
    public void onRequestFailure(Throwable t) {
        Toast.makeText(getBaseActivity(), "Проблемы с сетью", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthFailure() {

    }

    @Override
    public void onPutNewsComment(PutNewsCommentModel result) {
        presenter.loadComments(true, result.getId());
        edtComment.setText("");
    }

    @OnClick(R.id.btnSend)
    public void onSendCommentClick(View view) {
        presenter.putComment(edtComment.getText().toString(), replyTo);
    }

    public void onCommentClick(NewsCommentModel comment) {
        rlReplyContainer.setVisibility(View.VISIBLE);
        tvReplyText.setText(comment.getComment());
        replyTo = comment;
    }

    @OnClick(R.id.ibCancelReply)
    public void onIbReplyCancelClick(View v) {
        rlReplyContainer.setVisibility(View.GONE);
        tvReplyText.setText("");
        replyTo = null;
    }

    @Override
    public void onPutRateNewsComment(PutRateNewsCommentModel result) {
        NewsCommentModel item = newsCommentsRecyclerAdapter.get(result.getId());
        if (item != null) {
            item.setRating(result.getRaiting());
            item.setCanRate(false);
            newsCommentsRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void rateComment(String id, String sign) {
        presenter.putRateComment(id, sign);
    }

    public void onRateCommentPlusButtonClick(NewsCommentModel item) {
        rateComment(item.getId(), "+");
    }

    public void onRateCommentMinusButtonClick(NewsCommentModel item) {
        rateComment(item.getId(), "-");
    }
}
