package com.staticvillage.trakt_android.season;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class AllSeasonsFragment extends ExtendedResultFragment {
    public static AllSeasonsFragment newInstance() {
        return new AllSeasonsFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getAllSeasons("99718", extended)
                .map(response -> gson.toJson(response));
    }
}
