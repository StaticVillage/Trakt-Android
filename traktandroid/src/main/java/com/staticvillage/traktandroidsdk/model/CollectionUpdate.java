package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joelparrish on 11/3/16.
 */

public class CollectionUpdate {
    @SerializedName("added")
    private UpdateResult added;
    @SerializedName("updated")
    private UpdateResult updated;
    @SerializedName("existing")
    private UpdateResult existing;
    @SerializedName("not_found")
    private NotFoundResult notFound;

    public UpdateResult getAdded() {
        return added;
    }

    public void setAdded(UpdateResult added) {
        this.added = added;
    }

    public UpdateResult getUpdated() {
        return updated;
    }

    public void setUpdated(UpdateResult updated) {
        this.updated = updated;
    }

    public UpdateResult getExisting() {
        return existing;
    }

    public void setExisting(UpdateResult existing) {
        this.existing = existing;
    }

    public NotFoundResult getNotFound() {
        return notFound;
    }

    public void setNotFound(NotFoundResult notFound) {
        this.notFound = notFound;
    }
}
