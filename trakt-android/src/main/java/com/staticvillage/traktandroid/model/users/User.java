package com.staticvillage.traktandroid.model.users;

import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroid.model.common.Identifier;
import com.staticvillage.traktandroid.model.users.Images;

/**
 * Created by joelparrish on 10/24/16.
 */

public class User {
    @SerializedName("username")
    private String username;
    @SerializedName("private")
    private Boolean privateUser;
    @SerializedName("name")
    private String name;
    @SerializedName("vip")
    private Boolean vip;
    @SerializedName("vip_ep")
    private Boolean vipEp;
    @SerializedName("ids")
    private Identifier ids;
    @SerializedName("joined_at")
    private String joinedAt;
    @SerializedName("location")
    private String location;
    @SerializedName("about")
    private String about;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private Integer age;
    @SerializedName("images")
    private Images images;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(Boolean privateUser) {
        this.privateUser = privateUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Boolean getVipEp() {
        return vipEp;
    }

    public void setVipEp(Boolean vipEp) {
        this.vipEp = vipEp;
    }

    public Identifier getIds() {
        return ids;
    }

    public void setIds(Identifier ids) {
        this.ids = ids;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
