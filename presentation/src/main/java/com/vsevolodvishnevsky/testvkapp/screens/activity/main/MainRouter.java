package com.vsevolodvishnevsky.testvkapp.screens.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.testvkapp.base.BaseRouter;
import com.vsevolodvishnevsky.testvkapp.screens.activity.auth.AuthActivity;

public class MainRouter extends BaseRouter {
    public MainRouter(AppCompatActivity activity) {
        super(activity);
    }

    public void navigateToAuthorizationActivity(boolean isFirstAuthorization) {
        Intent intent = AuthActivity.createIntent(getActivity(), isFirstAuthorization);
        getActivity().startActivity(intent);
    }
}
