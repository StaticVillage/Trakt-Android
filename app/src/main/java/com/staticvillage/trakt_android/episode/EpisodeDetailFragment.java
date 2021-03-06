package com.staticvillage.trakt_android.episode;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

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
