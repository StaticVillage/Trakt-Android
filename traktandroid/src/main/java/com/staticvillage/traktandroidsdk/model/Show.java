package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joelparrish on 10/23/16.
 */

public class Show {
    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private Integer year;
    @SerializedName("ids")
    private Identifier ids;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("airs")
    private AirTime airs;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("certification")
    private String certification;
    @SerializedName("network")
    private String network;
    @SerializedName("country")
    private String country;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("trailer")
    private String trailer;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("status")
    private String status;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("votes")
    private Integer votes;
    @SerializedName("language")
    private String language;
    @SerializedName("available_translations")
    private List<String> availableTranslations;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("aired_episodes")
    private Integer airedEpisodes;

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

    public AirTime getAirs() {
        return airs;
    }

    public void setAirs(AirTime airs) {
        this.airs = airs;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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

    public Integer getAiredEpisodes() {
        return airedEpisodes;
    }

    public void setAiredEpisodes(Integer airedEpisodes) {
        this.airedEpisodes = airedEpisodes;
    }
}
