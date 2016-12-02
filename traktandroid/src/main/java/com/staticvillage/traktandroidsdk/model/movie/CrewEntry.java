package com.staticvillage.traktandroidsdk.model.movie;

import com.staticvillage.traktandroidsdk.model.Movie;

/**
 * Created by joelparrish on 12/1/16.
 */

public class CrewEntry {
    private String job;
    private Movie movie;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
