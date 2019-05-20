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
    public static final String IS_FIRST_AUTHORIZATION = "isFirstAuthorization";

    public AuthorizationFragment() {
        // Required empty public constructor
    }

    public static AuthorizationFragment getInstance(boolean isFirstAuthorization) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FIRST_AUTHORIZATION, isFirstAuthorization);
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
        boolean isFirstAuthorization = getArguments().getBoolean(IS_FIRST_AUTHORIZATION);
        View root = super.onCreateView(inflater, container, savedInstanceState);
        WebView webView = root.findViewById(R.id.web_view);
        Button authButton = root.findViewById(R.id.auth_button);
        authButton.setVisibility(isFirstAuthorization ? View.VISIBLE : View.GONE);
        if (isFirstAuthorization) {
            authButton.setOnClickListener(v -> {
                authButton.setVisibility(View.GONE);
                authorize(webView);
            });
        } else {
            authorize(webView);
        }
        return root;
    }

    private void authorize(WebView webView) {
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(webView, request.getUrl().toString());

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(Constants.CALLBACK_URL) & (!url.contains("error"))) {
                    viewModel.saveAuthorizationData(url);
                    return true;
                }
                return false;
            }
        });
        String url = Uri.parse("https://oauth.vk.com/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", "6988108")
                .appendQueryParameter("redirect_uri", "https://oauth.vk.com/blank.html")
                .appendQueryParameter("display", "mobile")
                .appendQueryParameter("scope", "friends")
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("v", Constants.VERSION)
                .build().toString();
        webView.loadUrl(url);
    }
}
