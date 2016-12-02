package com.staticvillage.trakt_android;

import android.app.Application;
import android.preference.PreferenceManager;

import com.staticvillage.traktandroidsdk.TraktService;

import static com.staticvillage.trakt_android.Constants.PREF_AUTH_TOKEN;

/**
 * Created by joelparrish on 11/4/16.
 */

public class App extends Application {
    private static TraktService traktService;

    public static TraktService getTraktService() {
        return traktService;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String authToken = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_AUTH_TOKEN, "");

        traktService = new TraktService(getString(R.string.client_id),
                getString(R.string.client_secret),
                getString(R.string.redirect_uri),
                authToken);
    }
}
