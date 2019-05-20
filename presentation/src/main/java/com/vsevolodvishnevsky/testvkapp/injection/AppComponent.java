package com.vsevolodvishnevsky.testvkapp.injection;

import com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization.AuthorizationViewModel;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(AuthorizationViewModel authorizationViewModel);

    void inject(MainViewModel mainViewModel);
}
