package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/31/16.
 */

public class EpisodeActivity {
    @SerializedName("watched_at")
    private String watchedAt;
    @SerializedName("collected_at")
    private String collectedAt;
    @SerializedName("rated_at")
    private String ratedAt;
    @SerializedName("watchlisted_at")
    private String watchlistedAt;
    @SerializedName("commented_at")
    private String commentedAt;
    @SerializedName("paused_at")
    private String pausedAt;

    public String getWatchedAt() {
        return watchedAt;
    }

    public void setWatchedAt(String watchedAt) {
        this.watchedAt = watchedAt;
    }

    public String getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(String collectedAt) {
        this.collectedAt = collectedAt;
    }

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

    public String getPausedAt() {
        return pausedAt;
    }

    public void setPausedAt(String pausedAt) {
        this.pausedAt = pausedAt;
    }
}
