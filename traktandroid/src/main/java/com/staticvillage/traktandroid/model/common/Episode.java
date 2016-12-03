package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joelparrish on 10/23/16.
 */

public class Episode {
    private int season;
    private int number;
    private String title;
    private Identifier ids;
    private String overview;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("updated_at")
    private String updatedAt;
    private double rating;
    private int votes;
    @SerializedName("available_translations")
    private List<String> availableTranslations;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Identifier getIds() {
        return ids;
    }

    public void setIds(Identifier ids) {
        this.ids = ids;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<String> getAvailableTranslations() {
        return availableTranslations;
    }

    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }
}
