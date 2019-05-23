package com.vsevolodvishnevsky.testvkapp.screens.activity.start;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.testvkapp.base.BaseRouter;
import com.vsevolodvishnevsky.testvkapp.screens.activity.auth.AuthActivity;
import com.vsevolodvishnevsky.testvkapp.screens.activity.main.MainActivity;

public class StartRouter extends BaseRouter {
    public StartRouter(AppCompatActivity activity) {
        super(activity);
    }

    public void navigateToAuthorizationActivity(boolean isFirstAuthorization) {
        Intent intent = AuthActivity.createIntent(getActivity(), isFirstAuthorization);
        getActivity().startActivity(intent);
    }

    public void navigateToMainActivity() {
        Intent intent = MainActivity.createIntent(getActivity());
        getActivity().startActivity(intent);
    }
}
