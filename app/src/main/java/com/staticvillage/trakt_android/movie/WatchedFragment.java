package com.staticvillage.trakt_android.movie;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class WatchedFragment extends ExtendedResultFragment {
    public static WatchedFragment newInstance() {
        return new WatchedFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getWatchedMovies("weekly", 0, 7, extended, null)
                .map(response -> gson.toJson(response));
    }
}
