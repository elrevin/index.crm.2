package me.elrevin.indexcrm.common.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.elrevin.indexcrm.R;
import me.elrevin.indexcrm.mvp.model.NewsModel;

public class NewsListAdapter extends CustomBaseAdapter<NewsModel> {
    public NewsListAdapter(ArrayList<NewsModel> objects, Context ctx) {
        super(objects, ctx);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.news_item_on_main_screen, parent, false);
        }

        NewsModel n = (NewsModel) getItem(position);

        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvAutor = (TextView) view.findViewById(R.id.tvAuthor);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvShortText = (TextView) view.findViewById(R.id.tvShortText);

        tvDate.setText(n.getDate());
        tvAutor.setText(n.getAutor());
        tvTitle.setText(n.getName());
        tvShortText.setText(n.getShortText());

        return view;
    }
}
