package com.vsevolodvishnevsky.testvkapp.base;


import android.support.v4.app.FragmentActivity;

public abstract class BaseRouter {
    private FragmentActivity activity;

    public BaseRouter(FragmentActivity activity) {
        this.activity = activity;
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void back() {
        getActivity().onBackPressed();
    }

    public void releaseActivity() {
        activity = null;
    }
}
