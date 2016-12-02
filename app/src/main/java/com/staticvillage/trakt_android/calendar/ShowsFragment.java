package com.staticvillage.trakt_android.calendar;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedCalendarResultFragment;

import java.util.Date;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class ShowsFragment extends ExtendedCalendarResultFragment {
    public static ShowsFragment newInstance() {
        return new ShowsFragment();
    }

    @Override
    public void setUrl() {
        endpointUrl.setText("calendars/all/shows/{start_date}/{days}");
    }

    @Override
    public Observable<String> getResult() {
        Date startDate = getStartDate();
        int days = getDays();
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getShows(startDate, days, extended, null)
                .map(response -> gson.toJson(response));
    }
}
