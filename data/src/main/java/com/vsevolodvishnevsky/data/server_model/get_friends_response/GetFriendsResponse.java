
package com.vsevolodvishnevsky.data.server_model.get_friends_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFriendsResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
