package com.staticvillage.traktandroidsdk.util;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.staticvillage.traktandroidsdk.util.Constants.COUNTRIES;
import static com.staticvillage.traktandroidsdk.util.Constants.EXTENDED;
import static com.staticvillage.traktandroidsdk.util.Constants.GENRES;
import static com.staticvillage.traktandroidsdk.util.Constants.LANGUAGES;
import static com.staticvillage.traktandroidsdk.util.Constants.LIMIT;
import static com.staticvillage.traktandroidsdk.util.Constants.PAGE;
import static com.staticvillage.traktandroidsdk.util.Constants.PERIOD;
import static com.staticvillage.traktandroidsdk.util.Constants.QUERY;
import static com.staticvillage.traktandroidsdk.util.Constants.RATINGS;
import static com.staticvillage.traktandroidsdk.util.Constants.RUNTIMES;
import static com.staticvillage.traktandroidsdk.util.Constants.YEARS;

/**
 * Created by joelparrish on 11/3/16.
 */

public class FilterBuilder {
    protected Map<String, String> map;

    public FilterBuilder() {
        map = new HashMap<>();
    }

    public FilterBuilder setQuery(String query) {
        map.put(QUERY, query);
        return this;
    }

    public FilterBuilder setYears(String years) {
        map.put(YEARS, years);
        return this;
    }

    public FilterBuilder setGenres(String... genres) {
        String genresStr = TextUtils.join(",", genres);
        map.put(GENRES, genresStr);
        return this;
    }

    public FilterBuilder setLanguages(String... languages) {
        String languagesStr = TextUtils.join(",", languages);
        map.put(LANGUAGES, languagesStr);
        return this;
    }

    public FilterBuilder setCountries(String... countries) {
        String countriesStr = TextUtils.join(",", countries);
        map.put(COUNTRIES, countriesStr);
        return this;
    }

    public FilterBuilder setRuntimes(int min, int max) {
        String runtimes = String.format(Locale.US, "%d-%d", min, max);
        map.put(RUNTIMES, runtimes);
        return this;
    }

    public FilterBuilder setRatings(int min, int max) {
        String ratings = String.format(Locale.US, "%d-%d", min, max);
        map.put(RATINGS, ratings);
        return this;
    }

    public Map<String, String> build() {
        return map;
    }
}
