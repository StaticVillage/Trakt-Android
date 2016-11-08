package com.staticvillage.traktandroidsdk.model;

/**
 * Created by joelparrish on 10/23/16.
 */

public class TrendingShow {
    private int watchers;
    private Show show;

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
