package com.staticvillage.traktandroidsdk;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TraktInterceptor implements Interceptor {
    private String authToken;
    private String apiKey;

    public TraktInterceptor() {
    }

    /**
     * Get Oauth token
     * @return Oauth token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set Oauth token
     * @param authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * get API token for the app
     * @return API token
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * set API token for the app
     * @param apiKey API token
     */
    public void setApiKey(@NonNull String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Content-type", "application/json")
                .header("trakt-api-key", apiKey)
                .header("trakt-api-version", "2");

        if (authToken != null && !authToken.isEmpty()) {
            requestBuilder.header("Authorization", String.format(Locale.US, "Bearer %s", authToken));
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
