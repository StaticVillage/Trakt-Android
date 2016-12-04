package com.staticvillage.traktandroid.model.common;

/**
 * Created by joelparrish on 12/3/16.
 */

public class Stats {
    private int watchers;
    private int plays;
    private int collectors;
    private int comments;
    private int lists;
    private int votes;

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getCollectors() {
        return collectors;
    }

    public void setCollectors(int collectors) {
        this.collectors = collectors;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLists() {
        return lists;
    }

    public void setLists(int lists) {
        this.lists = lists;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
