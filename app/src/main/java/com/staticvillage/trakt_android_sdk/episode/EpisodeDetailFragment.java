package com.staticvillage.trakt_android_sdk.episode;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class EpisodeDetailFragment extends ExtendedResultFragment {
    public static EpisodeDetailFragment newInstance() {
        return new EpisodeDetailFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getEpisode("99718", 1, 1, extended)
                .map(response -> {
                    return gson.toJson(response);
                });
    }
}
