package com.staticvillage.trakt_android.genre;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class MovieGenreFragment extends ExtendedResultFragment {
    public static MovieGenreFragment newInstance() {
        return new MovieGenreFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getShowGenres()
                .map(response -> gson.toJson(response));
    }
}
