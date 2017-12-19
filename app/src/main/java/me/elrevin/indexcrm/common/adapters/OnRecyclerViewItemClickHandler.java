package me.elrevin.indexcrm.common.adapters;

public interface OnRecyclerViewItemClickHandler<T> {
    void onClick(T item, int index);
}
