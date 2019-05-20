package com.vsevolodvishnevsky.testvkapp.screens.fragments.main;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseMVVMFragment;
import com.vsevolodvishnevsky.testvkapp.databinding.FragmentMainBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseMVVMFragment<FragmentMainBinding, MainViewModel> {


    public MainFragment() {
    }


    @Override
    public int provideLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public MainViewModel provideViewModel() {
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setTokenExpiredCallback((TokenExpiredCallback) getActivity());
        mainViewModel.loadData();
        return mainViewModel;
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
