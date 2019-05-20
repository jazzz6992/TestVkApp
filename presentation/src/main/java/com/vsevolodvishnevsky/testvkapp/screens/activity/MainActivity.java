package com.vsevolodvishnevsky.testvkapp.screens.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.screens.routers.MainRouter;
import com.vsevolodvishnevsky.testvkapp.util.TokenValidator;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainRouter router = new MainRouter(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getString(Constants.ACCESS_TOKEN, null) != null) {
            if (TokenValidator.isTokenValid(sharedPreferences)) {
                router.navigateToMainFragment();
            } else {
                router.navigateToAuthorizationFragment(false);
            }
        } else {
            router.navigateToAuthorizationFragment(true);
        }
    }
}
