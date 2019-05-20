package com.vsevolodvishnevsky.domain.repository;

import com.vsevolodvishnevsky.domain.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface DataRepository {
    Observable<List<User>> getUsersByIds(String ids, String token, String appVersion);

    Observable<List<Integer>> getFriendsIds(String id, String token, String appVersion, int count);
}
