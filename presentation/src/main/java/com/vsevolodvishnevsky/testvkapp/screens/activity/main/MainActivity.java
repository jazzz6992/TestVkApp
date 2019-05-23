package com.vsevolodvishnevsky.testvkapp.screens.activity.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.base.BaseMVVMActivity;
import com.vsevolodvishnevsky.testvkapp.databinding.ActivityMainBinding;
import com.vsevolodvishnevsky.testvkapp.screens.activity.auth.AuthActivity;
import com.vsevolodvishnevsky.testvkapp.screens.activity.start.StartRouter;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding, MainViewModel, MainRouter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.loadData();
    }


    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public MainRouter provideRouter() {
        return new MainRouter(this);
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
