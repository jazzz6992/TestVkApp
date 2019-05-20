package com.vsevolodvishnevsky.data.api;

import com.vsevolodvishnevsky.data.server_model.get_friends_response.GetFriendsResponse;
import com.vsevolodvishnevsky.data.server_model.get_user_response.GetUserResponse;
import com.vsevolodvishnevsky.domain.constants.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApi {
    @GET("method/users.get")
    Observable<GetUserResponse> getUsersById(@Query("user_ids") String userIds, @Query(Constants.ACCESS_TOKEN) String token, @Query("v") String version);

    @GET("method/friends.get")
    Observable<GetFriendsResponse> getFriendsIds(@Query("user_id") String userId, @Query(Constants.ACCESS_TOKEN) String token, @Query("v") String version, @Query("count") int count);
}
