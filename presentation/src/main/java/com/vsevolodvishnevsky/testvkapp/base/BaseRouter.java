package com.vsevolodvishnevsky.testvkapp.base;


import android.support.v7.app.AppCompatActivity;

public abstract class BaseRouter {
    private AppCompatActivity activity;

    public BaseRouter(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void back() {
        getActivity().onBackPressed();
    }

    public void releaseActivity() {
        activity = null;
    }
}
