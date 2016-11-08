package com.staticvillage.trakt_android_sdk.sync;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class MovieCollectionFragment extends ExtendedResultFragment {
    public static MovieCollectionFragment newInstance() {
        return new MovieCollectionFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getMovieCollection(extended)
                .map(response -> gson.toJson(response));
    }
}
