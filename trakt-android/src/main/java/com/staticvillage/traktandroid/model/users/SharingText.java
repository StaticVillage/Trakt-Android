package com.staticvillage.traktandroid.model.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/24/16.
 */

public class SharingText {
    @SerializedName("watching")
    @Expose
    private String watching;
    @SerializedName("watched")
    @Expose
    private String watched;

    public String getWatching() {
        return watching;
    }

    public void setWatching(String watching) {
        this.watching = watching;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }
}
