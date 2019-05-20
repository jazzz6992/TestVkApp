package com.vsevolodvishnevsky.data.api;

import com.vsevolodvishnevsky.data.server_model.get_user_response.GetUserResponse;
import com.vsevolodvishnevsky.data.server_model.get_user_response.User;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;

public class VKService {
    private VKApi vkApi;

    @Inject
    public VKService(VKApi vkApi) {
        this.vkApi = vkApi;
    }

    public Observable<List<com.vsevolodvishnevsky.domain.entity.User>> getUsersById(String ids, String token, String appVersion) {
        return vkApi.getUsersById(ids, token, appVersion).map(this::mapUsers);
    }

    public Observable<List<Integer>> getFriendsIds(String id, String token, String appVersion, int count) {
        return vkApi.getFriendsIds(id, token, appVersion, count).map(getFriendsResponse -> getFriendsResponse.getResponse().getItems());
    }

    private List<com.vsevolodvishnevsky.domain.entity.User> mapUsers(GetUserResponse getUserResponse) {
        List<com.vsevolodvishnevsky.domain.entity.User> users = new ArrayList<>();
        List<User> result = getUserResponse.getUsers();
        for (User r : result) {
            com.vsevolodvishnevsky.domain.entity.User user = new com.vsevolodvishnevsky.domain.entity.User();
            user.setId(r.getId());
            user.setFirstName(r.getFirstName());
            user.setLastName(r.getLastName());
            user.setCanAccessClosed(r.getCanAccessClosed());
            user.setIsClosed(r.getIsClosed());
            users.add(user);
        }
        return users;
    }
}
