package com.vsevolodvishnevsky.data.repository;

import com.vsevolodvishnevsky.data.api.VKService;
import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.domain.repository.DataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DataRepositoryImpl implements DataRepository {
    private VKService vkService;

    @Inject
    public DataRepositoryImpl(VKService vkService) {
        this.vkService = vkService;
    }

    @Override
    public Observable<List<User>> getUsersByIds(String ids, String token, String appVersion) {
        return vkService.getUsersById(ids, token, appVersion);
    }

    @Override
    public Observable<List<Integer>> getFriendsIds(String id, String token, String appVersion, int count) {
        return vkService.getFriendsIds(id, token, appVersion, count);
    }
}
