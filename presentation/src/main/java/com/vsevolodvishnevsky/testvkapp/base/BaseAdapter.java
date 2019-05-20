package com.vsevolodvishnevsky.testvkapp.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<Model, ViewModel extends BaseItemViewModel<Model>> extends RecyclerView.Adapter<BaseViewHolder<Model, ViewModel, ?>> {
    protected List<Model> items = new ArrayList<>();

    @Override
    public void onBindViewHolder(BaseViewHolder<Model, ViewModel, ?> holder, int position) {
        Model model = items.get(position);
        holder.bind(model);
    }

    public void setItems(List<Model> items) {
        this.items.clear();
        addItems(items);
    }

    public void addItems(List<Model> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
