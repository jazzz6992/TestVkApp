package com.vsevolodvishnevsky.testvkapp.injection;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.vsevolodvishnevsky.data.api.VKApi;
import com.vsevolodvishnevsky.data.repository.DataRepositoryImpl;
import com.vsevolodvishnevsky.domain.executor.PostExecutionThread;
import com.vsevolodvishnevsky.domain.repository.DataRepository;
import com.vsevolodvishnevsky.testvkapp.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {


    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public PostExecutionThread getUiThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://api.vk.com/method/").build();
    }

    @Provides
    @Singleton
    public VKApi getVkApi(Retrofit retrofit) {
        return retrofit.create(VKApi.class);
    }

    @Provides
    @Singleton
    public DataRepository getDataRepository(VKApi vkApi) {
        return new DataRepositoryImpl(vkApi);
    }

}
