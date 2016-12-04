package com.staticvillage.traktandroid.util;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by joelparrish on 11/3/16.
 */

public class RetrofitUtil {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType PLAIN_TEXT_TYPE = MediaType.parse("text/plain; charset=utf-8");

    /**
     * Convert string data to plain text request body
     *
     * @param data data
     * @return plain text request body
     */
    public static RequestBody toRequestBody(String data) {
        return RequestBody.create(PLAIN_TEXT_TYPE, data);
    }

    /**
     * Convert map to json request body
     *
     * @param map parameter map
     * @return json request body
     */
    public static RequestBody toRequestBody(Map<String, ?> map) {
        return RequestBody.create(JSON_MEDIA_TYPE, new JSONObject(map).toString());
    }

    /**
     * Convert json object to json request body
     *
     * @param jsonObject json object
     * @return json request body
     */
    public static RequestBody toRequestBody(JSONObject jsonObject) {
        return RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString());
    }
}
