package com.staticvillage.trakt_android.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class TranslationsFragment extends ExtendedResultFragment {
    public static TranslationsFragment newInstance() {
        return new TranslationsFragment();
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
                .getMovieTranslations("308", "en")
                .map(response -> gson.toJson(response));
    }
}
