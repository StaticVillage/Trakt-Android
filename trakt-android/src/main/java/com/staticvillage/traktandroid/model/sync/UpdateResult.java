package com.staticvillage.traktandroid.model.sync;

/**
 * Created by joelparrish on 11/3/16.
 */

public class UpdateResult {
    private int movies;
    private int episodes;

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
}
