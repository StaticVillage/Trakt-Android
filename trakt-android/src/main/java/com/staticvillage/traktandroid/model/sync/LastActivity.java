package com.staticvillage.traktandroid.model.sync;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/31/16.
 */

public class LastActivity {
    @SerializedName("all")
    private String all;
    @SerializedName("movies")
    private MovieActivity movies;
    @SerializedName("episodes")
    private EpisodeActivity episodes;
    @SerializedName("shows")
    private ShowActivity shows;
    @SerializedName("seasons")
    private SeasonActivity seasons;
    @SerializedName("comments")
    private CommentActivity comments;
    @SerializedName("lists")
    private ListActivity lists;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public MovieActivity getMovies() {
        return movies;
    }

    public void setMovies(MovieActivity movies) {
        this.movies = movies;
    }

    public EpisodeActivity getEpisodes() {
        return episodes;
    }

    public void setEpisodes(EpisodeActivity episodes) {
        this.episodes = episodes;
    }

    public ShowActivity getShows() {
        return shows;
    }

    public void setShows(ShowActivity shows) {
        this.shows = shows;
    }

    public SeasonActivity getSeasons() {
        return seasons;
    }

    public void setSeasons(SeasonActivity seasons) {
        this.seasons = seasons;
    }

    public CommentActivity getComments() {
        return comments;
    }

    public void setComments(CommentActivity comments) {
        this.comments = comments;
    }

    public ListActivity getLists() {
        return lists;
    }

    public void setLists(ListActivity lists) {
        this.lists = lists;
    }
}
