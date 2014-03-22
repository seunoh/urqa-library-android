package com.urqa.android.common;

import com.urqa.android.UrQAHelper;
import com.urqa.android.collector.DateCollector;
import com.urqa.android.eventpath.EventPathManager;
import com.urqa.android.level.ErrorLevel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ApplicationReport implements ToJson {

    private final String mUrQAVersion;
    private final String mTag;
    private final int mLevel;
    private final String mDateTime;
    private final String mDevice;
    private final String mOSVersion;
    private final List<EventPathReport> mEventPathReportList;


    public ApplicationReport(Builder builder) {
        mUrQAVersion = builder.mUrQAVersion;
        mTag = builder.mTag;
        mLevel = builder.mLevel;
        mDateTime = builder.mDateTime;
        mDevice = builder.mDevice;
        mOSVersion = builder.mOSVersion;
        mEventPathReportList = builder.mEventPathReportList;
    }

    public String getUrQAVersion() {
        return mUrQAVersion;
    }

    public String getTag() {
        return mTag;
    }

    public int getLevel() {
        return mLevel;
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

    public List<EventPathReport> getEventPathReportList() {
        return mEventPathReportList;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Keys.UrQA_VERSION.getKey(), getUrQAVersion());
        jsonObject.put(Keys.TAG.getKey(), getTag());
        jsonObject.put(Keys.ERROR_LEVEL.getKey(), getLevel());
        jsonObject.put(Keys.DATE.getKey(), getDateTime());
        jsonObject.put(Keys.DEVICE.getKey(), getDevice());
        jsonObject.put(Keys.OS_VERSION.getKey(), getOSVersion());
        jsonObject.put(Keys.EVENT_PATH.getKey(), toJsonArray(getEventPathReportList()));

        return jsonObject;
    }


    private JSONArray toJsonArray(List<EventPathReport> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (EventPathReport path : list) {
            jsonArray.put(path.toJson());
        }

        return jsonArray;

    }

    public static class Builder {
        private String mUrQAVersion;
        private String mTag;
        private int mLevel;
        private String mDateTime;
        private String mDevice;
        private String mOSVersion;
        private List<EventPathReport> mEventPathReportList;

        public Builder(String tag, ErrorLevel level) {
            this.mUrQAVersion = UrQAHelper.getInstance().getVersion();
            this.mEventPathReportList = EventPathManager.getInstance().getEventPath();
            this.mDateTime = DateCollector.getDate();
            this.mDevice = android.os.Build.MODEL;
            this.mOSVersion = android.os.Build.VERSION.RELEASE;
            this.mTag = tag;
            this.mLevel = level.getLevel();

        }

        public ApplicationReport build() {
            return new ApplicationReport(this);
        }
    }
}
