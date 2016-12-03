package com.staticvillage.traktandroid.model.sync;

import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroid.model.common.Movie;

/**
 * Created by joelparrish on 10/25/16.
 */

public class CollectionMovie {
    @SerializedName("collected_at")
    private String collectedAt;
    private Movie movie;

    public String getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(String collectedAt) {
        this.collectedAt = collectedAt;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
