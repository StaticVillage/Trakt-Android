package com.staticvillage.traktandroidsdk.model.show;

import com.staticvillage.traktandroidsdk.model.Movie;
import com.staticvillage.traktandroidsdk.model.Show;

/**
 * Created by joelparrish on 12/1/16.
 */

public class CrewEntry {
    private String job;
    private Show show;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
