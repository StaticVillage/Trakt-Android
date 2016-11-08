package com.staticvillage.trakt_android_sdk.movie;

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

public class ReleasesFragment extends ExtendedResultFragment {
    public static ReleasesFragment newInstance() {
        return new ReleasesFragment();
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
        return App.getTraktService()
                .getMovieReleases("308", "us")
                .map(response -> gson.toJson(response));
    }
}
