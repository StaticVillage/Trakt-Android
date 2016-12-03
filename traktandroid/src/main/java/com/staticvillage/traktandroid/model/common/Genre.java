package com.staticvillage.traktandroid.model.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 10/30/16.
 */

public class Genre {
    @SerializedName("name")
    private String name;
    @SerializedName("slug")
    private String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
