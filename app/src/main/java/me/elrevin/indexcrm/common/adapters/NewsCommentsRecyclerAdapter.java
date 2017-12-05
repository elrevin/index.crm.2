package me.elrevin.indexcrm.common.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.NewsCommentModel;
import me.elrevin.indexcrm.ui.fragment.NewsItemFragment;

public class NewsCommentsRecyclerAdapter extends RecyclerView.Adapter<NewsCommentsRecyclerAdapter.ViewHolder> {

    private ArrayList<NewsCommentModel> list;
    private NewsItemFragment fragment;

    public NewsCommentsRecyclerAdapter(NewsItemFragment fragment) {
        this.fragment = fragment;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_comment_item_on_news_screen, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsCommentModel item = list.get(position);
        holder.setItem(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onCommentClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearList() {
        list.clear();
    }

    public void clearList(boolean notify) {
        list.clear();
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void addAll(List<NewsCommentModel> items) {
        list.addAll(items);
    }

    public void addAll(List<NewsCommentModel> items, boolean notify) {
        list.addAll(items);
        if (notify) {
            notifyDataSetChanged();
        }
    }


    public int getPosition(NewsCommentModel item) {
        for (int i = 0; i < getItemCount(); i++) {
            if (list.get(i).getId().equals(item.getId())) {
                return i;
            }
        }
        return -1;
    }

    public int getPosition(String id) {
        for (int i = 0; i < getItemCount(); i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cvNewsCommentItem)
        CardView cv;

        @BindView(R.id.tvAuthor)
        TextView tvAuthor;

        @BindView(R.id.tvDate)
        TextView tvDate;

        @BindView(R.id.tvRating)
        TextView tvRating;

        @BindView(R.id.tvText)
        TextView tvText;

        @BindView(R.id.llCommentWrapper)
        LinearLayout llCommentWrapper;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void setItem(NewsCommentModel item) {
            tvAuthor.setText(item.getName());
            tvDate.setText(item.getDate());

            int raiting = Integer.parseInt(item.getRating() == null ? "0" : item.getRating());

            String raitingStr;

            if (raiting > 0) {
                raitingStr = "+" + Integer.toString(raiting);
            } else {
                raitingStr = Integer.toString(raiting);
            }

            tvRating.setText(raitingStr);
            tvText.setText(item.getComment());

            int nest = item.getNest() + 1;
            int padding = 32 * nest;

            llCommentWrapper.setPadding(padding, 0, llCommentWrapper.getPaddingRight(), 0);
        }
    }
}
