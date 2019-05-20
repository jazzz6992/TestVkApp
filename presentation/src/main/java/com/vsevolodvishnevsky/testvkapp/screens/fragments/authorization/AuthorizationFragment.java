package com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization;


import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseMVVMFragment;
import com.vsevolodvishnevsky.testvkapp.databinding.FragmentAuthorizationBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorizationFragment extends BaseMVVMFragment<FragmentAuthorizationBinding, AuthorizationViewModel> {
    public static final String IS_FIRST_AUTHORIZATION_KEY = "isFirstAuthorization";
    public static final String RESPONSE_TYPE = "token";
    public static final String SCOPE = "friends";
    public static final String DISPLAY = "mobile";
    public static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    public static final String AUTHORIZATION_URL = "https://oauth.vk.com/authorize";
    private Button authButton;
    private WebView webView;

    public AuthorizationFragment() {
        // Required empty public constructor
    }

    public static AuthorizationFragment getInstance(boolean isFirstAuthorization) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FIRST_AUTHORIZATION_KEY, isFirstAuthorization);
        AuthorizationFragment fragment = new AuthorizationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int provideLayoutId() {
        return R.layout.fragment_authorization;
    }

    @Override
    public AuthorizationViewModel provideViewModel() {
        AuthorizationViewModel authorizationViewModel = ViewModelProviders.of(this).get(AuthorizationViewModel.class);
        authorizationViewModel.setAuthorizationCallback((AuthorizationCallback) getActivity());
        return authorizationViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean isFirstAuthorization = getArguments().getBoolean(IS_FIRST_AUTHORIZATION_KEY);
        View root = super.onCreateView(inflater, container, savedInstanceState);
        webView = root.findViewById(R.id.web_view);
        authButton = root.findViewById(R.id.auth_button);
        if (isFirstAuthorization) {
            authButton.setOnClickListener(v -> {
                authButton.setVisibility(View.GONE);
                authorize();
            });
        } else {
            authorize();
        }
        return root;
    }

    private void authorize() {
        authButton.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(webView, request.getUrl().toString());

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URL) & (!url.contains("error"))) {
                    viewModel.saveAuthorizationData(url);
                    return true;
                }
                return false;
            }
        });
        String url = Uri.parse(AUTHORIZATION_URL)
                .buildUpon()
                .appendQueryParameter("client_id", String.valueOf(getResources().getInteger(R.integer.com_vk_sdk_AppId)))
                .appendQueryParameter("redirect_uri", REDIRECT_URL)
                .appendQueryParameter("display", DISPLAY)
                .appendQueryParameter("scope", SCOPE)
                .appendQueryParameter("response_type", RESPONSE_TYPE)
                .appendQueryParameter("v", Constants.VERSION)
                .build().toString();
        webView.loadUrl(url);
    }
}
