package com.staticvillage.traktandroid.model.search;

import com.staticvillage.traktandroid.model.common.Episode;
import com.staticvillage.traktandroid.model.common.Show;

/**
 * Created by joelparrish on 10/31/16.
 */

public class EpisodeQueryResult extends QueryResult {
    private Episode episode;
    private Show show;

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
