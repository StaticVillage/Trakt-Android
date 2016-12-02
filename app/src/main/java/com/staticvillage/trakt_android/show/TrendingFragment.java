package com.staticvillage.trakt_android.show;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class TrendingFragment extends ExtendedResultFragment {
    public static TrendingFragment newInstance() {
        return new TrendingFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getTrendingShows(0, 10, extended, null)
                .map(response -> gson.toJson(response));
    }
}
