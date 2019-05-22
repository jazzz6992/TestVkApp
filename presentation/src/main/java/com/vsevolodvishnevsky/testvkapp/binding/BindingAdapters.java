package com.vsevolodvishnevsky.testvkapp.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BindingAdapters {

    @BindingAdapter("adapter")
    public static void initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("load_url")
    public static void loadUrl(WebView webView, String url) {
        if (url != null) {
            webView.loadUrl(url);
        }
    }

    @BindingAdapter("init")
    public static void init(WebView webView, WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
