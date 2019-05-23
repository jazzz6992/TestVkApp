package com.vsevolodvishnevsky.testvkapp.screens.activity.start;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.util.TokenValidator;


public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StartRouter router = new StartRouter(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getString(Constants.ACCESS_TOKEN, null) != null) {
            if (TokenValidator.isTokenValid(sharedPreferences)) {
                router.navigateToMainActivity();
            } else {
                router.navigateToAuthorizationActivity(false);
            }
        } else {
            router.navigateToAuthorizationActivity(true);
        }
    }
}
