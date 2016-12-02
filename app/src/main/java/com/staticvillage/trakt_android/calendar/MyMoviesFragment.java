package com.staticvillage.trakt_android.calendar;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.common.ExtendedCalendarResultFragment;

import java.util.Date;

import rx.Observable;

/**
 * Created by joelparrish on 11/6/16.
 */

public class MyMoviesFragment extends ExtendedCalendarResultFragment {
    public static MyMoviesFragment newInstance() {
        return new MyMoviesFragment();
    }

    @Override
    public void setUrl() {
        endpointUrl.setText("calendars/my/movies/{start_date}/{days}");
    }

    @Override
    public Observable<String> getResult() {
        Date startDate = getStartDate();
        int days = getDays();
        boolean extended = extendedCheckBox.isChecked();
        return App.getTraktService()
                .getMyMovies(startDate, days, extended, null)
                .map(response -> gson.toJson(response));
    }
}
