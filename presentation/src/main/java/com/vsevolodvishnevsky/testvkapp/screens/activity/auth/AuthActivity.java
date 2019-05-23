package com.vsevolodvishnevsky.testvkapp.screens.activity.auth;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseMVVMActivity;
import com.vsevolodvishnevsky.testvkapp.databinding.ActivityAuthBinding;

public class AuthActivity extends BaseMVVMActivity<ActivityAuthBinding, AuthorizationViewModel, AuthRouter> {

    public static final String IS_FIRST_AUTHORIZATION_KEY = "isFirstAuthorization";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstAuthorization = getIntent().getBooleanExtra(IS_FIRST_AUTHORIZATION_KEY, false);
        if (!isFirstAuthorization) {
            viewModel.authorize();
        }
    }

    public static Intent createIntent(Context context, boolean isFirstAuthorization) {
        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra(IS_FIRST_AUTHORIZATION_KEY, isFirstAuthorization);
        return intent;
    }

    @Override
    public int provideLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    public AuthorizationViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(AuthorizationViewModel.class);
    }

    @Override
    public AuthRouter provideRouter() {
        return new AuthRouter(this);
    }
}
