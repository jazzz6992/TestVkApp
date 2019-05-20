package com.vsevolodvishnevsky.testvkapp.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

public class BindingAdapters {
    @BindingAdapter("adapter")
    public static void initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
