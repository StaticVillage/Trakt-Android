package com.staticvillage.traktandroid.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 12/3/16.
 */

public class Release {
    private String country;
    private String certification;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("release_type")
    private String releaseType;
    private String note;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
