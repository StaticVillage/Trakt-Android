package com.staticvillage.trakt_android_sdk.show;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.common.ExtendedResultFragment;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class RelatedFragment extends ExtendedResultFragment {
    public static RelatedFragment newInstance() {
        return new RelatedFragment();
    }

    @Override
    public Observable<String> getResult() {
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getRelatedShows("99718", extended)
                .map(response -> gson.toJson(response));
    }
}
