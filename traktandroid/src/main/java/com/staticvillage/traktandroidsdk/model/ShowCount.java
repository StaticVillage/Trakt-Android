package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/30/16.
 */

public class ShowCount {
    @SerializedName("watcher_count")
    private long watcherCount;
    @SerializedName("play_count")
    private long playCount;
    @SerializedName("collected_count")
    private long collectedCount;
    private Show show;

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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
