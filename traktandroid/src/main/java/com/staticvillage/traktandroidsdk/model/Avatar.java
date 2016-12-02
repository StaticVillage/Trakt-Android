package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/24/16.
 */

public class Avatar {
    @SerializedName("full")
    @Expose
    private String full;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
}
