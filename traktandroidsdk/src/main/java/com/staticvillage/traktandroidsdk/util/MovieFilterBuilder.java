package com.staticvillage.traktandroidsdk.util;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.staticvillage.traktandroidsdk.util.Constants.CERTIFICATIONS;
import static com.staticvillage.traktandroidsdk.util.Constants.COUNTRIES;
import static com.staticvillage.traktandroidsdk.util.Constants.GENRES;
import static com.staticvillage.traktandroidsdk.util.Constants.LANGUAGES;
import static com.staticvillage.traktandroidsdk.util.Constants.QUERY;
import static com.staticvillage.traktandroidsdk.util.Constants.RUNTIMES;
import static com.staticvillage.traktandroidsdk.util.Constants.YEARS;

/**
 * Created by joelparrish on 11/3/16.
 */

public class MovieFilterBuilder extends FilterBuilder {
    public MovieFilterBuilder setCertifications(String certifications) {
        map.put(CERTIFICATIONS, certifications);
        return this;
    }
}
