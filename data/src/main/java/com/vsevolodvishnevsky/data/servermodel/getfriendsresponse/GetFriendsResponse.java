
package com.vsevolodvishnevsky.data.servermodel.getfriendsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFriendsResponse {

    @SerializedName("response")
    @Expose
    private GetFriendsResponsePayload getFriendsResponsePayload;

    public GetFriendsResponsePayload getGetFriendsResponsePayload() {
        return getFriendsResponsePayload;
    }

    public void setGetFriendsResponsePayload(GetFriendsResponsePayload getFriendsResponsePayload) {
        this.getFriendsResponsePayload = getFriendsResponsePayload;
    }
}
