package com.vsevolodvishnevsky.data.api;

import com.vsevolodvishnevsky.data.servermodel.getfriendsresponse.GetFriendsResponse;
import com.vsevolodvishnevsky.data.servermodel.getusersresponse.GetUsersResponse;
import com.vsevolodvishnevsky.domain.constants.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApi {
    @GET("users.get")
    Observable<GetUsersResponse> getUsersById(@Query("user_ids") String userIds, @Query(Constants.ACCESS_TOKEN) String token, @Query("v") String version);

    @GET("friends.get")
    Observable<GetFriendsResponse> getFriendsIds(@Query("user_id") String userId, @Query(Constants.ACCESS_TOKEN) String token, @Query("v") String version, @Query("count") int count);
}
