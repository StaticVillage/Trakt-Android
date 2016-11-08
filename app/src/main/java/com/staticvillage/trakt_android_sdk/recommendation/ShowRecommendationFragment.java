package com.staticvillage.trakt_android_sdk.recommendation;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class ShowRecommendationFragment extends ExtendedResultFragment {
    public static ShowRecommendationFragment newInstance() {
        return new ShowRecommendationFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getShowRecommendations(10, extended)
                .map(response -> gson.toJson(response));
    }
}
