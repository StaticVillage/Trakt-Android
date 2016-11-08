package com.staticvillage.trakt_android_sdk.search;

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

public class MovieSearchFragment extends ExtendedResultFragment {
    public static MovieSearchFragment newInstance() {
        return new MovieSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        searchContainer.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public Observable<String> getResult() {
        String query = searchText.getText().toString();
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .searchMovies(query, extended, null)
                .map(response -> gson.toJson(response));
    }
}
