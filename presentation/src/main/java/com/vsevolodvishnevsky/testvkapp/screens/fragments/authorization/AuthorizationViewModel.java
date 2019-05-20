package com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.app.App;
import com.vsevolodvishnevsky.testvkapp.base.BaseViewModel;
import com.vsevolodvishnevsky.testvkapp.injection.AppComponent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class AuthorizationViewModel extends BaseViewModel {
    @SuppressLint("StaticFieldLeak")
    @Inject
    public Context context;
    private AuthorizationCallback authorizationCallback;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public void saveAuthorizationData(String url) {
        Uri uri = Uri.parse(url.replace("#", "?"));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long expiresAt = Long.valueOf(uri.getQueryParameter(Constants.EXPIRES_IN)) * 1000 + new Date().getTime();

        preferences.edit()
                .putString(Constants.ACCESS_TOKEN, uri.getQueryParameter(Constants.ACCESS_TOKEN))
                .putLong(Constants.EXPIRES_AT, expiresAt)
                .putString(Constants.USER_ID, uri.getQueryParameter(Constants.USER_ID))
                .apply();
        authorizationCallback.onAuthorized();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        context = null;
        authorizationCallback = null;
    }

    public void setAuthorizationCallback(AuthorizationCallback authorizationCallback) {
        this.authorizationCallback = authorizationCallback;
    }
}
