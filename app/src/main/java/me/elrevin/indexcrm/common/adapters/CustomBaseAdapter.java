package me.elrevin.indexcrm.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import me.elrevin.indexcrm.mvp.model.BaseListItemModel;

public abstract class CustomBaseAdapter<T extends BaseListItemModel> extends BaseAdapter {

    protected ArrayList<T> objects;

    protected Context ctx;
    protected LayoutInflater lInflater;

    public CustomBaseAdapter(ArrayList<T> objects, Context ctx) {
        this.objects = objects;
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) this.ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (objects.size() > position && position > 0) {
            T obj = objects.get(position);
            return Integer.valueOf(obj.getId());
        }
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
