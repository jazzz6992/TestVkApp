package com.vsevolodvishnevsky.testvkapp.screens.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization.AuthorizationCallback;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization.AuthorizationFragment;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.main.MainFragment;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.main.TokenExpiredCallback;
import com.vsevolodvishnevsky.testvkapp.util.TokenValidator;


public class MainActivity extends AppCompatActivity implements AuthorizationCallback, TokenExpiredCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getString(Constants.ACCESS_TOKEN, null) != null) {
            if (TokenValidator.isTokenValid(sharedPreferences)) {
                navigateToMainFragment();
            } else {
                navigateToAuthorizationFragment(false);
            }
        } else {
            navigateToAuthorizationFragment(true);
        }
    }

    private void navigateToAuthorizationFragment(boolean isFirstAuthorization) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, AuthorizationFragment.getInstance(isFirstAuthorization));
        fragmentTransaction.commit();
    }

    private void navigateToMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, MainFragment.getInstance());
        fragmentTransaction.commit();
    }

    @Override
    public void onAuthorized() {
        navigateToMainFragment();
    }

    @Override
    public void onTokenExpired() {
        navigateToAuthorizationFragment(false);
    }
}
