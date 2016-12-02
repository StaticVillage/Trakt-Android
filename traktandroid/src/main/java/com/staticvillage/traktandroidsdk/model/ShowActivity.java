package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/31/16.
 */

public class ShowActivity {
    @SerializedName("rated_at")
    private String ratedAt;
    @SerializedName("watchlisted_at")
    private String watchlistedAt;
    @SerializedName("commented_at")
    private String commentedAt;
    @SerializedName("hidden_at")
    private String hiddenAt;

    public String getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(String ratedAt) {
        this.ratedAt = ratedAt;
    }

    public String getWatchlistedAt() {
        return watchlistedAt;
    }

    public void setWatchlistedAt(String watchlistedAt) {
        this.watchlistedAt = watchlistedAt;
    }

    public String getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(String commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getHiddenAt() {
        return hiddenAt;
    }

    public void setHiddenAt(String hiddenAt) {
        this.hiddenAt = hiddenAt;
    }
}
