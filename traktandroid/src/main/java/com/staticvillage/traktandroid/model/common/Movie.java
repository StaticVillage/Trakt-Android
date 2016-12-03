package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joelparrish on 10/30/16.
 */

public class Movie {
    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private Integer year;
    @SerializedName("ids")
    private Identifier ids;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("overview")
    private String overview;
    @SerializedName("released")
    private String released;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("trailer")
    private Object trailer;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("rating")
    private Integer rating;
    @SerializedName("votes")
    private Integer votes;
    @SerializedName("language")
    private String language;
    @SerializedName("available_translations")
    private List<String> availableTranslations;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("certification")
    private String certification;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Identifier getIds() {
        return ids;
    }

    public void setIds(Identifier ids) {
        this.ids = ids;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getTrailer() {
        return trailer;
    }

    public void setTrailer(Object trailer) {
        this.trailer = trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getAvailableTranslations() {
        return availableTranslations;
    }

    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }
}
