package com.vsevolodvishnevsky.testvkapp.screens.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Fragment fragment;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (sharedPreferences.getString(Constants.ACCESS_TOKEN, null) != null) {
            if (!TokenValidator.isTokenExpired(sharedPreferences)) {
                fragment = MainFragment.getInstance();
            } else {
                fragment = AuthorizationFragment.getInstance(false);
            }
        } else {
            fragment = AuthorizationFragment.getInstance(true);
        }
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onAuthorized() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, MainFragment.getInstance());
        fragmentTransaction.commit();
    }

    @Override
    public void onTokenExpired() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, AuthorizationFragment.getInstance(false));
        fragmentTransaction.commit();
    }
}
