package com.staticvillage.trakt_android_sdk.movie;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class SummaryFragment extends ExtendedResultFragment {
    public static SummaryFragment newInstance() {
        return new SummaryFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getMovieDetails("308", extended)
                .map(response -> gson.toJson(response));
    }
}
