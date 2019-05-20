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

public abstract class BaseMVVMFragment<Binding extends ViewDataBinding, ViewModel extends BaseViewModel> extends Fragment {

    protected Binding binding;
    protected ViewModel viewModel;

    public abstract int provideLayoutId();

    public abstract ViewModel provideViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false);
        viewModel = provideViewModel();
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }
}
