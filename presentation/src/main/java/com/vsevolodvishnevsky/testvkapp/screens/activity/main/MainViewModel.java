package com.vsevolodvishnevsky.testvkapp.screens.activity.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.domain.repository.DataRepository;
import com.vsevolodvishnevsky.testvkapp.R;
import com.vsevolodvishnevsky.testvkapp.app.App;
import com.vsevolodvishnevsky.testvkapp.base.BaseViewModel;
import com.vsevolodvishnevsky.testvkapp.util.TokenValidator;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel<MainRouter> {
    @Inject
    public DataRepository dataRepository;
    @SuppressLint("StaticFieldLeak")
    @Inject
    public Context context;

    public ObservableField<User> owner = new ObservableField<>();

    private SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    private UserAdapter userAdapter = new UserAdapter();


    public void loadData() {
        if (TokenValidator.isTokenValid(sharedPreferences)) {
            getUserInfo();
            getFriends();
        } else {
            router.navigateToAuthorizationActivity(false);
        }
    }

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    private void getUserInfo() {
        compositeDisposable.add(dataRepository.getUsersByIds(sharedPreferences.getString(Constants.USER_ID, null),
                sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                context.getResources().getString(R.string.version))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    owner.set(users.get(0));
                    owner.notifyChange();
                }, this::handleException));
    }

    private void getFriends() {
        compositeDisposable.add(dataRepository.getFriendsIds(sharedPreferences.getString(Constants.USER_ID, null),
                sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                context.getResources().getString(R.string.version), 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ids -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int id : ids) {
                        stringBuilder.append(id).append(",");
                    }
                    if (stringBuilder.lastIndexOf(",") != -1) {
                        stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
                    }
                    if (stringBuilder.length() > 0) {
                        String userIds = stringBuilder.toString();
                        compositeDisposable.add(dataRepository.getUsersByIds(userIds,
                                sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                                context.getResources().getString(R.string.version))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(users -> {
                                    userAdapter.setItems(users);
                                }, this::handleException));
                    }
                }, this::handleException));
    }

    public UserAdapter getUserAdapter() {
        return userAdapter;
    }

    @Override
    public void handleException(Throwable t) {
        Log.e(Constants.LOG_TAG, t.getLocalizedMessage());
        if (t instanceof IOException) {
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCleared() {
        context = null;
        sharedPreferences = null;
        super.onCleared();
    }
}
