package com.staticvillage.trakt_android_sdk.show;

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

public class RatingsFragment extends ExtendedResultFragment {
    public static RatingsFragment newInstance() {
        return new RatingsFragment();
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
                .getShowRatings("99718")
                .map(response -> gson.toJson(response));
    }
}
