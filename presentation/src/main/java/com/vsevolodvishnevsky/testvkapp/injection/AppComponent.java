package com.vsevolodvishnevsky.testvkapp.injection;

import com.vsevolodvishnevsky.testvkapp.screens.activity.auth.AuthorizationViewModel;
import com.vsevolodvishnevsky.testvkapp.screens.activity.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(AuthorizationViewModel authorizationViewModel);

    void inject(MainViewModel mainViewModel);
}
