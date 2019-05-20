package com.vsevolodvishnevsky.testvkapp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolodvishnevsky.testvkapp.BR;

public abstract class BaseMVVMFragment<Binding extends ViewDataBinding, ViewModel extends BaseViewModel, R extends BaseRouter> extends Fragment {

    protected Binding binding;
    protected ViewModel viewModel;

    public abstract int provideLayoutId();

    public abstract ViewModel provideViewModel();

    public abstract R provideRouter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false);
        viewModel = provideViewModel();
        viewModel.attachRouter(provideRouter());
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.onStop();
    }
}
