package com.staticvillage.traktandroidsdk.model;

/**
 * Created by joelparrish on 10/31/16.
 */

public class MovieQueryResult extends QueryResult {
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
