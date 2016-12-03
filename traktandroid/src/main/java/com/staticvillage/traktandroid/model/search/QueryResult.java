package com.staticvillage.traktandroid.model.search;

/**
 * Created by joelparrish on 10/31/16.
 */

public class QueryResult {
    private String type;
    private float score;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
