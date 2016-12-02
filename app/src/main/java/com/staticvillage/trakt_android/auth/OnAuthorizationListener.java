package com.staticvillage.trakt_android.auth;

/**
 * Created by joelparrish on 11/4/16.
 */

public interface OnAuthorizationListener {
    void onAuthorized();

    void onUnauthorized();

    void onFailure();
}
