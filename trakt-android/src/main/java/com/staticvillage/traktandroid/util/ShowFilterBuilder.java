package com.staticvillage.traktandroid.util;

import static com.staticvillage.traktandroid.util.Constants.CERTIFICATIONS;
import static com.staticvillage.traktandroid.util.Constants.NETWORKS;
import static com.staticvillage.traktandroid.util.Constants.STATUS;

/**
 * Created by joelparrish on 11/3/16.
 */

public class ShowFilterBuilder extends FilterBuilder {
    public ShowFilterBuilder setCertifications(String certifications) {
        map.put(CERTIFICATIONS, certifications);
        return this;
    }

    public ShowFilterBuilder setNetworks(String networks) {
        map.put(NETWORKS, networks);
        return this;
    }

    public ShowFilterBuilder setStatus(String status) {
        map.put(STATUS, status);
        return this;
    }
}
