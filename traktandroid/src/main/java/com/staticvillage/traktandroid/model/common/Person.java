package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 12/1/16.
 */

public class Person {
    @SerializedName("name")
    private String name;
    @SerializedName("ids")
    private Identifier ids;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("death")
    private String death;
    @SerializedName("birthplace")
    private String birthplace;
    @SerializedName("homepage")
    private String homepage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Identifier getIds() {
        return ids;
    }

    public void setIds(Identifier ids) {
        this.ids = ids;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
