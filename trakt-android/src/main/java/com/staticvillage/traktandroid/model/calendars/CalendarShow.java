package com.staticvillage.traktandroid.model.calendars;

import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroid.model.common.Episode;
import com.staticvillage.traktandroid.model.common.Show;

/**
 * Created by joelparrish on 10/30/16.
 */

public class CalendarShow {
    @SerializedName("first_aired")
    private String firstAired;
    private Episode episode;
    private Show show;

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
