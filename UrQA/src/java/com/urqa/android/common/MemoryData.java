package com.urqa.android.common;

import com.urqa.android.collector.MemoryManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class MemoryData implements ToJson {
    private final int mAvailSdCard;
    private final int mAppMemTotal;
    private final int mAppMemFree;
    private final int mAppMemMax;
    private final int mSysMemLow;


    public MemoryData(Builder builder) {
        mAvailSdCard = builder.mAvailSdCard;
        mAppMemTotal = builder.mAppMemTotal;
        mAppMemFree = builder.mAppMemFree;
        mAppMemMax = builder.mAppMemMax;
        mSysMemLow = builder.mSysMemLow;
    }


    public int getAvailSdCard() {
        return mAvailSdCard;
    }

    public int getAppMemTotal() {
        return mAppMemTotal;
    }

    public int getAppMemFree() {
        return mAppMemFree;
    }

    public int getAppMemMax() {
        return mAppMemMax;
    }

    public int getSysMemLow() {
        return mSysMemLow;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("availsdcard", getAvailSdCard());
        jsonObject.put("appmemtotal", getAppMemTotal());
        jsonObject.put("appmemfree", getAppMemFree());
        jsonObject.put("appmemmax", getAppMemMax());
        jsonObject.put("sysmemlow", getSysMemLow());

        return jsonObject;
    }

    public static class Builder {

        private int mAvailSdCard;
        private int mAppMemTotal;
        private int mAppMemFree;
        private int mAppMemMax;
        private int mSysMemLow;

        public Builder(MemoryManager manager) {
            mAvailSdCard = manager.getAvailableExternalMemorySizeMegaByte();
            mAppMemTotal = manager.getTotalMemoryMegaByte();
            mAppMemFree = manager.getFreeMemoryMegaByte();
            mAppMemMax = manager.getMaxMemoryMegaByte();
            mSysMemLow = manager.getSystemLowMemory() ? 1 : 0;
        }


        public MemoryData build() {
            return new MemoryData(this);
        }
    }
}
