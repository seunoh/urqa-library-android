package com.urqa.android.common;

import com.urqa.android.collector.AppManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class AppData implements ToJson {


    private final int mGpsOn;
    private final int mWifiOn;
    private final int mMobileOn;
    private final int mBatteryLevel;
    private final int mRooted;
    private final String mKernelVersion;
    private final String mLocale;
    private final String mCountry;

    public AppData(Builder builder) {
        mGpsOn = builder.mGpsOn;
        mWifiOn = builder.mWifiOn;
        mMobileOn = builder.mMobileOn;
        mBatteryLevel = builder.mBatteryLevel;
        mRooted = builder.mRooted;
        mKernelVersion = builder.mKernelVersion;
        mLocale = builder.mLocale;
        mCountry = builder.mCountry;
    }

    public int getGpsOn() {
        return mGpsOn;
    }

    public int getWifiOn() {
        return mWifiOn;
    }

    public int getMobileOn() {
        return mMobileOn;
    }

    public int getBatteryLevel() {
        return mBatteryLevel;
    }

    public int getRooted() {
        return mRooted;
    }

    public String getKernelVersion() {
        return mKernelVersion;
    }

    public String getLocale() {
        return mLocale;
    }

    public String getCountry() {
        return mCountry;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gpson", getGpsOn());
        jsonObject.put("wifion", getWifiOn());
        jsonObject.put("mobileon", getMobileOn());
        jsonObject.put("batterylevel", getBatteryLevel());
        jsonObject.put("rooted", getRooted());
        jsonObject.put("kernelversion", getKernelVersion());
        jsonObject.put("locale", getLocale());
        jsonObject.put("country", getCountry());
        return jsonObject;
    }

    public static class Builder {
        private int mGpsOn;
        private int mWifiOn;
        private int mMobileOn;
        private int mBatteryLevel;
        private int mRooted;
        private String mKernelVersion;
        private String mLocale;
        private String mCountry;

        public Builder(AppManager manager) {
            this.mGpsOn = manager.getGps() ? 1 : 0;
            this.mWifiOn = manager.isWifi() ? 1 : 0;
            this.mMobileOn = manager.isMobile() ? 1 : 0;
            this.mBatteryLevel = manager.getBatteryLevel();
            this.mRooted = manager.checkRoot() ? 1 : 0;
            this.mKernelVersion = manager.getLinuxKernelVersion();
            this.mLocale = manager.getDisplayLanguage();
            this.mCountry = manager.getCountry();

        }


        public AppData build() {
            return new AppData(this);
        }

    }
}
