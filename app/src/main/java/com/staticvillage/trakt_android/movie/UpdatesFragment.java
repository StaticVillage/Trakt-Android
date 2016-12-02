package com.staticvillage.trakt_android.movie;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class UpdatesFragment extends ExtendedResultFragment {
    public static UpdatesFragment newInstance() {
        return new UpdatesFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getMovieUpdates(0, 10, extended)
                .map(response -> gson.toJson(response));
    }
}
