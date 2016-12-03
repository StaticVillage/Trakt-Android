package com.staticvillage.traktandroid.model.sync;

import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroid.model.common.Show;

/**
 * Created by joelparrish on 10/25/16.
 */

public class CollectionShow {
    @SerializedName("collected_at")
    private String collectedAt;
    private Show show;

    public String getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(String collectedAt) {
        this.collectedAt = collectedAt;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
