package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/30/16.
 */

public class MovieCount {
    @SerializedName("watcher_count")
    private long watcherCount;
    @SerializedName("play_count")
    private long playCount;
    @SerializedName("collected_count")
    private long collectedCount;
    private Movie movie;

    public long getWatcherCount() {
        return watcherCount;
    }

    public void setWatcherCount(long watcherCount) {
        this.watcherCount = watcherCount;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    public long getCollectedCount() {
        return collectedCount;
    }

    public void setCollectedCount(long collectedCount) {
        this.collectedCount = collectedCount;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
