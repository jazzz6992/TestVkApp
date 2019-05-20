package com.vsevolodvishnevsky.testvkapp.screens.routers;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseRouter;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.authorization.AuthorizationFragment;
import com.vsevolodvishnevsky.testvkapp.screens.fragments.main.MainFragment;

public class MainRouter extends BaseRouter {
    public MainRouter(FragmentActivity activity) {
        super(activity);
    }

    public void navigateToAuthorizationFragment(boolean isFirstAuthorization) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, AuthorizationFragment.getInstance(isFirstAuthorization));
        fragmentTransaction.commit();
    }

    public void navigateToMainFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, MainFragment.getInstance());
        fragmentTransaction.commit();
    }
}
