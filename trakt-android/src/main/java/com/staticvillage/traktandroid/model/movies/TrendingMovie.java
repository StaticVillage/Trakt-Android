package com.staticvillage.traktandroid.model.movies;

import com.staticvillage.traktandroid.model.common.Movie;

/**
 * Created by joelparrish on 10/23/16.
 */

public class TrendingMovie {
    private int watchers;
    private Movie movie;

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
