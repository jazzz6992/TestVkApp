package com.vsevolodvishnevsky.testvkapp.screens.activity.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.testvkapp.base.BaseRouter;
import com.vsevolodvishnevsky.testvkapp.screens.activity.main.MainActivity;

public class AuthRouter extends BaseRouter {
    public AuthRouter(AppCompatActivity activity) {
        super(activity);
    }

    public void navigateToMainActivity() {
        Intent intent = MainActivity.createIntent(getActivity());
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
