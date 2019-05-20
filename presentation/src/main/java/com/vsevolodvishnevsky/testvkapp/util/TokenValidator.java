package com.vsevolodvishnevsky.testvkapp.util;

import android.content.SharedPreferences;

import com.vsevolodvishnevsky.domain.constants.Constants;

import java.util.Date;

public class TokenValidator {
    public static boolean isTokenValid(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(Constants.EXPIRES_AT, 0) - 10000 > new Date().getTime();
    }
}
