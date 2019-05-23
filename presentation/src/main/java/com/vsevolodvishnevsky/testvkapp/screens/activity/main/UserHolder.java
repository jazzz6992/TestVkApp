package com.vsevolodvishnevsky.testvkapp.screens.activity.main;

import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.testvkapp.base.BaseViewHolder;
import com.vsevolodvishnevsky.testvkapp.databinding.UserItemBinding;

public class UserHolder extends BaseViewHolder<User, ItemUserViewModel, UserItemBinding> {
    public UserHolder(UserItemBinding binding, ItemUserViewModel viewModel) {
        super(binding, viewModel);
    }
}
