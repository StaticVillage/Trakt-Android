package com.staticvillage.trakt_android_sdk.recommendation;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class MovieRecommendationFragment extends ExtendedResultFragment {
    public static MovieRecommendationFragment newInstance() {
        return new MovieRecommendationFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getMovieRecommendations(10, extended)
                .map(response -> gson.toJson(response));
    }
}
