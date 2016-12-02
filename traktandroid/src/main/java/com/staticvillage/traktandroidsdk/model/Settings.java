package com.staticvillage.traktandroidsdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.staticvillage.traktandroidsdk.model.auth.Account;

/**
 * Created by joelparrish on 10/24/16.
 */

public class Settings {
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("connections")
    @Expose
    private Connections connections;
    @SerializedName("sharing_text")
    @Expose
    private SharingText sharingText;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public SharingText getSharingText() {
        return sharingText;
    }

    public void setSharingText(SharingText sharingText) {
        this.sharingText = sharingText;
    }
}
