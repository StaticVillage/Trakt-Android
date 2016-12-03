package com.staticvillage.traktandroid.util;

import static com.staticvillage.traktandroid.util.Constants.CERTIFICATIONS;

/**
 * Created by joelparrish on 11/3/16.
 */

public class MovieFilterBuilder extends FilterBuilder {
    public MovieFilterBuilder setCertifications(String certifications) {
        map.put(CERTIFICATIONS, certifications);
        return this;
    }
}
