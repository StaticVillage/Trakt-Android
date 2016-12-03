package com.staticvillage.traktandroid.model.sync;

import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroid.model.common.Episode;
import com.staticvillage.traktandroid.model.common.Movie;
import com.staticvillage.traktandroid.model.common.Season;
import com.staticvillage.traktandroid.model.common.Show;

import java.util.List;

/**
 * Created by joelparrish on 11/3/16.
 */

public class NotFoundResult {
    @SerializedName("movies")
    private List<Movie> movies;
    @SerializedName("shows")
    private List<Show> shows;
    @SerializedName("seasons")
    private List<Season> seasons;
    @SerializedName("episodes")
    private List<Episode> episodes;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
