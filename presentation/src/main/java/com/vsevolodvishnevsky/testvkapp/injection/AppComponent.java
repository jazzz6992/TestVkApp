package com.vsevolodvishnevsky.testvkapp.injection;

import com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization.AuthorizationViewModel;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(AuthorizationViewModel authorizationViewModel);

    void inject(MainViewModel mainViewModel);
}
