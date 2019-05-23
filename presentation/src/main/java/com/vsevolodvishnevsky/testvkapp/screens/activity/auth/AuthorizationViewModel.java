package com.vsevolodvishnevsky.testvkapp.screens.activity.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.app.App;
import com.vsevolodvishnevsky.testvkapp.base.BaseViewModel;

import java.util.Date;

import javax.inject.Inject;

public class AuthorizationViewModel extends BaseViewModel<AuthRouter> {


    @SuppressLint("StaticFieldLeak")
    @Inject
    public Context context;

    private ObservableField<String> observableUrl = new ObservableField<>();
    private final ObservableBoolean isAuthorizationInProgress = new ObservableBoolean(false);
    private WebViewClient webViewClient = new WebViewClient() {

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains(Constants.ERROR) || url.contains(Constants.CANCEL)) {
                isAuthorizationInProgress.set(false);
                return true;
            } else if (url.contains(Constants.LOGOUT)) {
                isAuthorizationInProgress.set(false);
                return false;
            } else if (url.startsWith(context.getResources().getString(R.string.redirect_url))) {
                saveAuthorizationData(url);
                return true;
            }
            return false;
        }
    };

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public ObservableField<String> getObservableUrl() {
        return observableUrl;
    }

    public WebViewClient getWebViewClient() {
        return webViewClient;
    }

    public ObservableBoolean getIsAuthorizationInProgress() {
        return isAuthorizationInProgress;
    }

    private void saveAuthorizationData(String url) {
        Uri uri = Uri.parse(url.replace("#", "?"));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long expiresAt = Long.valueOf(uri.getQueryParameter(Constants.EXPIRES_IN)) * 1000 + new Date().getTime();

        preferences.edit()
                .putString(Constants.ACCESS_TOKEN, uri.getQueryParameter(Constants.ACCESS_TOKEN))
                .putLong(Constants.EXPIRES_AT, expiresAt)
                .putString(Constants.USER_ID, uri.getQueryParameter(Constants.USER_ID))
                .apply();
        router.navigateToMainActivity();
    }

    public void authorize() {
        Resources resources = context.getResources();
        String url = Uri.parse(resources.getString(R.string.authorization_url))
                .buildUpon()
                .appendQueryParameter("client_id", String.valueOf(resources.getInteger(R.integer.appId)))
                .appendQueryParameter("redirect_uri", resources.getString(R.string.redirect_url))
                .appendQueryParameter("display", resources.getString(R.string.display))
                .appendQueryParameter("scope", resources.getString(R.string.scope))
                .appendQueryParameter("response_type", resources.getString(R.string.response_type))
                .appendQueryParameter("v", resources.getString(R.string.version))
                .build().toString();
        observableUrl.set(url);
        isAuthorizationInProgress.set(true);
    }

    @Override
    protected void onCleared() {
        context = null;
        super.onCleared();
    }
}
