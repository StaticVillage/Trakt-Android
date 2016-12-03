package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joelparrish on 10/31/16.
 */

public class Season extends StandardSeason {
    @SerializedName("season")
    private int season;
    @SerializedName("title")
    private String title;
    @SerializedName("number_abs")
    private Object numberAbs;
    @SerializedName("overview")
    private String overview;
    @SerializedName("rating")
    private double rating;
    @SerializedName("votes")
    private int votes;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("available_translations")
    private List<String> availableTranslations;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getNumberAbs() {
        return numberAbs;
    }

    public void setNumberAbs(Object numberAbs) {
        this.numberAbs = numberAbs;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public List<String> getAvailableTranslations() {
        return availableTranslations;
    }

    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }
}
