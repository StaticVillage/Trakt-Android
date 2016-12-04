package com.staticvillage.traktandroid.model.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/24/16.
 */

public class Images {
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
