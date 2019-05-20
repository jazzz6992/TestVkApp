package com.vsevolodvishnevsky.testvkapp.screens.fragments.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseAdapter;
import com.vsevolodvishnevsky.testvkapp.databinding.UserItemBinding;

public class UserAdapter extends BaseAdapter<User, ItemUserViewModel> {

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        UserItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_item, viewGroup, false);
        return new UserHolder(binding, new ItemUserViewModel());
    }
}
