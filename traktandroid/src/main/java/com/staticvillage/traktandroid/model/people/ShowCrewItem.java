package com.staticvillage.traktandroid.model.people;

import com.staticvillage.traktandroid.model.common.Show;

/**
 * Created by joelparrish on 12/1/16.
 */

public class ShowCrewItem {
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
