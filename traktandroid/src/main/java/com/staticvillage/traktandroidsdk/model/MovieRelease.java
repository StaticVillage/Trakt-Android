package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/31/16.
 */

public class MovieRelease {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("release_type")
    @Expose
    private String releaseType;
    @SerializedName("note")
    @Expose
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
