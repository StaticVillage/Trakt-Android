package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/24/16.
 */

public class Account {
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("time_24hr")
    @Expose
    private Boolean time24hr;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getTime24hr() {
        return time24hr;
    }

    public void setTime24hr(Boolean time24hr) {
        this.time24hr = time24hr;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
