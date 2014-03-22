package com.urqa.android.common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 1. 26..
 */
public class AuthReport implements ToJson {
    private String mApiKey;
    private String mAppVersion;

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public String getAppVersion() {
        return mAppVersion;
    }

    public void setAppVersion(String appVersion) {
        mAppVersion = appVersion;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Keys.API_KEY.getKey(), getApiKey());
            jsonObject.put(Keys.APP_VERSION.getKey(), getAppVersion());
        } catch (JSONException ignored) {
        }

        return jsonObject;
    }

}
