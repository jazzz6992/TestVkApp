package com.vsevolodvishnevsky.testvkapp.screens.fragments.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.preference.PreferenceManager;

import com.vsevolodvishnevsky.domain.constants.Constants;
import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.domain.interactors.GetFriendsIdsUseCase;
import com.vsevolodvishnevsky.domain.interactors.GetUsersByIdUseCase;
import com.vsevolodvishnevsky.testvkapp.app.App;
import com.vsevolodvishnevsky.testvkapp.base.BaseViewModel;
import com.vsevolodvishnevsky.testvkapp.util.TokenValidator;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {
    @Inject
    public GetUsersByIdUseCase getUsersByIdUseCase;
    @Inject
    public GetFriendsIdsUseCase getFriendsIdsUseCase;
    @SuppressLint("StaticFieldLeak")
    @Inject
    public Context context;

    private TokenExpiredCallback tokenExpiredCallback;

    public ObservableField<User> owner = new ObservableField<>();

    private SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    private UserAdapter userAdapter = new UserAdapter();

    public void loadData() {
        if (TokenValidator.isTokenValid(sharedPreferences)) {
            getUserInfo();
            getFriends();
        } else {
            tokenExpiredCallback.onTokenExpired();
        }
    }

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    private void getUserInfo() {
        compositeDisposable.add(getUsersByIdUseCase.execute(sharedPreferences.getString(Constants.USER_ID, null),
                sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                Constants.VERSION).subscribe(users -> {
            owner.set(users.get(0));
            owner.notifyChange();
        }));
    }

    private void getFriends() {
        compositeDisposable.add(getFriendsIdsUseCase.execute(sharedPreferences.getString(Constants.USER_ID, null),
                sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                Constants.VERSION, 5).subscribe(ids -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (int id : ids) {
                stringBuilder.append(id).append(",");
            }
            if (stringBuilder.lastIndexOf(",") != -1) {
                stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
            }
            if (stringBuilder.length() > 0) {
                String userIds = stringBuilder.toString();
                compositeDisposable.add(getUsersByIdUseCase.execute(userIds,
                        sharedPreferences.getString(Constants.ACCESS_TOKEN, null),
                        Constants.VERSION).subscribe(users -> {
                    userAdapter.setItems(users);
                }));
            }
        }));
    }

    public UserAdapter getUserAdapter() {
        return userAdapter;
    }

    public void setTokenExpiredCallback(TokenExpiredCallback tokenExpiredCallback) {
        this.tokenExpiredCallback = tokenExpiredCallback;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        context = null;
        tokenExpiredCallback = null;
    }


}
