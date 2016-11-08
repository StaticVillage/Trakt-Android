package com.staticvillage.trakt_android_sdk.show;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class LastEpisodeFragment extends ExtendedResultFragment {
    public static LastEpisodeFragment newInstance() {
        return new LastEpisodeFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getLastEpisode("99718", extended)
                .map(response -> gson.toJson(response));
    }
}
