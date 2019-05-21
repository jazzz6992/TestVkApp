package com.vsevolodvishnevsky.data.repository;

import com.vsevolodvishnevsky.data.api.VKApi;
import com.vsevolodvishnevsky.data.servermodel.getusersresponse.GetUsersResponse;
import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.domain.repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DataRepositoryImpl implements DataRepository {
    private VKApi vkApi;

    @Inject
    public DataRepositoryImpl(VKApi vkApi) {
        this.vkApi = vkApi;
    }

    @Override
    public Observable<List<User>> getUsersByIds(String ids, String token, String appVersion) {
        return vkApi.getUsersById(ids, token, appVersion).map(this::mapUsers);
    }

    @Override
    public Observable<List<Integer>> getFriendsIds(String id, String token, String appVersion, int count) {
        return vkApi.getFriendsIds(id, token, appVersion, count).map(getFriendsResponse -> getFriendsResponse.getGetFriendsResponsePayload().getItems());
    }

    private List<User> mapUsers(GetUsersResponse getUsersResponse) {
        List<User> users = new ArrayList<>();
        List<com.vsevolodvishnevsky.data.servermodel.getusersresponse.User> responseUsers = getUsersResponse.getUsers();
        for (com.vsevolodvishnevsky.data.servermodel.getusersresponse.User r : responseUsers) {
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
