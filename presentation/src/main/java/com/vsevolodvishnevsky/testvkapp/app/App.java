package com.vsevolodvishnevsky.testvkapp.app;

import android.app.Application;

import com.vsevolodvishnevsky.testvkapp.injection.AppComponent;
import com.vsevolodvishnevsky.testvkapp.injection.AppModule;
import com.vsevolodvishnevsky.testvkapp.injection.DaggerAppComponent;


public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
