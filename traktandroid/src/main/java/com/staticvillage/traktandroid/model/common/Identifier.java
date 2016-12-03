package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/23/16.
 */

public class Identifier {
    private long trakt;
    private String slug;
    private long tvdb;
    private String imdb;
    private long tmdb;
    @SerializedName("tvrage")
    private long tvrAge;

    public long getTrakt() {
        return trakt;
    }

    public void setTrakt(long trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public long getTvdb() {
        return tvdb;
    }

    public void setTvdb(long tvdb) {
        this.tvdb = tvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public long getTmdb() {
        return tmdb;
    }

    public void setTmdb(long tmdb) {
        this.tmdb = tmdb;
    }

    public long getTvrAge() {
        return tvrAge;
    }

    public void setTvrAge(long tvrAge) {
        this.tvrAge = tvrAge;
    }
}
