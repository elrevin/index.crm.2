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

public class NewsCommentsRecyclerAdapter extends RecyclerView.Adapter<NewsCommentsRecyclerAdapter.ViewHolder> {

    private ArrayList<NewsCommentModel> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_comment_item_on_news_screen, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsCommentModel item = list.get(position);
        holder.setItem(item);
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

            ButterKnife.bind(itemView);
        }

        public void setItem(NewsCommentModel item) {
            tvAuthor.setText(item.getName());
            tvDate.setText(item.getDate());
            tvRating.setText(item.getRating());
            tvText.setText(item.getComment());

            int nest = item.getNest() + 1;
            int margin = 16 * nest;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(margin, 16, 16, 16);
            llCommentWrapper.setLayoutParams(params);
        }
    }
}
