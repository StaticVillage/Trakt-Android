package com.staticvillage.traktandroid.util;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.staticvillage.traktandroid.exception.AuthException;

/**
 * Created by joelparrish on 12/4/16.
 */

public class TraktUtil {
    public static String getAuthCode(@NonNull Intent intent, String redirectUri) throws AuthException {
        Uri uri = intent.getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("code");
            if (code != null) {
                return code;
            } else if (uri.getQueryParameter("error") != null) {
                String message = uri.getQueryParameter("error");
                throw new AuthException(message);
            }
        }

        return null;
    }
}
