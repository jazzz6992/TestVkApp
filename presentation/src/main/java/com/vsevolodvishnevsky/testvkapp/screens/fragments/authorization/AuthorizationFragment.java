package com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseMVVMFragment;
import com.vsevolodvishnevsky.testvkapp.databinding.FragmentAuthorizationBinding;
import com.vsevolodvishnevsky.testvkapp.screens.routers.MainRouter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorizationFragment extends BaseMVVMFragment<FragmentAuthorizationBinding, AuthorizationViewModel, MainRouter> {
    public static final String IS_FIRST_AUTHORIZATION_KEY = "isFirstAuthorization";

    public AuthorizationFragment() {
        // Required empty public constructor
    }

    public static AuthorizationFragment getInstance(boolean isFirstAuthorization) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FIRST_AUTHORIZATION_KEY, isFirstAuthorization);
        AuthorizationFragment fragment = new AuthorizationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int provideLayoutId() {
        return R.layout.fragment_authorization;
    }

    @Override
    public AuthorizationViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(AuthorizationViewModel.class);
    }

    @Override
    public MainRouter provideRouter() {
        return new MainRouter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean isFirstAuthorization = getArguments().getBoolean(IS_FIRST_AUTHORIZATION_KEY);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (!isFirstAuthorization) {
            viewModel.authorize();
        }
        return view;
    }
}
