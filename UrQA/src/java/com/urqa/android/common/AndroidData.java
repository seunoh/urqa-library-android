package com.urqa.android.common;

import com.urqa.android.UrQAHelper;
import com.urqa.android.collector.DateCollector;
import com.urqa.android.eventpath.EventPathManager;
import com.urqa.android.rank.ErrorLevel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class AndroidData implements ToJson {

    private final String mSdkVersion;
    private final String mTag;
    private final int mRank;
    private final String mDateTime;
    private final String mDevice;
    private final String mOSVersion;
    private final List<EventPath> mEventPaths;


    public AndroidData(Builder builder) {
        mSdkVersion = builder.mSdkVersion;
        mTag = builder.mTag;
        mRank = builder.mRank;
        mDateTime = builder.mDateTime;
        mDevice = builder.mDevice;
        mOSVersion = builder.mOSVersion;
        mEventPaths = builder.mEventPaths;
    }

    public String getSdkVersion() {
        return mSdkVersion;
    }

    public String getTag() {
        return mTag;
    }

    public int getRank() {
        return mRank;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getDevice() {
        return mDevice;
    }

    public String getOSVersion() {
        return mOSVersion;
    }

    public List<EventPath> getEventPaths() {
        return mEventPaths;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sdkversion", getSdkVersion());
        jsonObject.put("tag", getTag());
        jsonObject.put("rank", getRank());
        jsonObject.put("datetime", getDateTime());
        jsonObject.put("device", getDevice());
        jsonObject.put("osversion", getOSVersion());
        jsonObject.put("eventpaths", toJsonArray(getEventPaths()));

        return jsonObject;
    }


    private JSONArray toJsonArray(List<EventPath> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (EventPath path : list) {
            jsonArray.put(path.toJson());
        }

        return jsonArray;

    }

    public static class Builder {
        private String mSdkVersion;
        private String mTag;
        private int mRank;
        private String mDateTime;
        private String mDevice;
        private String mOSVersion;
        private List<EventPath> mEventPaths;

        public Builder(String tag, ErrorLevel level) {
            UrQAHelper helper = UrQAHelper.getInstance();
            this.mSdkVersion = helper.getUrQAVersion();
            this.mEventPaths = EventPathManager.getInstance().getEventPath();
            this.mDateTime = DateCollector.getDate();
            this.mDevice = android.os.Build.MODEL;
            this.mOSVersion = android.os.Build.VERSION.RELEASE;
            this.mTag = tag;
            this.mRank = level.getValue();

        }

        public AndroidData build() {
            return new AndroidData(this);
        }
    }
}
