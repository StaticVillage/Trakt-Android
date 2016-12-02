package com.staticvillage.trakt_android.season;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class SeasonRatingsFragment extends ExtendedResultFragment {
    public static SeasonRatingsFragment newInstance() {
        return new SeasonRatingsFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getSeasonRatings("99718", 1, extended)
                .map(response -> gson.toJson(response));
    }
}
