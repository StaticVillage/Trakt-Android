package com.staticvillage.trakt_android_sdk.sync;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class ShowCollectionFragment extends ExtendedResultFragment {
    public static ShowCollectionFragment newInstance() {
        return new ShowCollectionFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getShowCollection(extended)
                .map(response -> gson.toJson(response));
    }
}
