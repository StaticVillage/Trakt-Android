package com.staticvillage.trakt_android_sdk.episode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class EpisodeRatingFragment extends ExtendedResultFragment {
    public static EpisodeRatingFragment newInstance() {
        return new EpisodeRatingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        extendedContainer.setVisibility(View.GONE);
        return view;
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getEpisodeRating("99718", 1, 1)
                .map(response -> gson.toJson(response));
    }
}
