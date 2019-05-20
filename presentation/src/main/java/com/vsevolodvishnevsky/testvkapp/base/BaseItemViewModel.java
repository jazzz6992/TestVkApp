package com.vsevolodvishnevsky.testvkapp.base;

import android.databinding.BaseObservable;

public abstract class BaseItemViewModel<Model> extends BaseObservable {
    private Model item;


    public Model getItem() {
        return item;
    }

    public void setItem(Model item) {
        this.item = item;
        notifyChange();
    }
}
