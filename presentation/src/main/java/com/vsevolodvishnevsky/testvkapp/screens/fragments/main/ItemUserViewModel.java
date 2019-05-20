package com.vsevolodvishnevsky.testvkapp.screens.fragments.main;

import android.databinding.Bindable;

import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.testvkapp.base.BaseItemViewModel;

public class ItemUserViewModel extends BaseItemViewModel<User> {
    @Bindable
    public String getName() {
        return getItem().getFirstName() + " " + getItem().getLastName();
    }
}
