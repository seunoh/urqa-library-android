package com.urqa.android.common;

import com.urqa.android.collector.MemoryManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class MemoryReport implements ToJson {
    private final long mAvailableExternal;
    private final long mMemoryTotal;
    private final long mMemoryFree;
    private final long mMemoryMax;
    private final long mSystemMemoryLow;


    public MemoryReport(Builder builder) {
        mAvailableExternal = builder.mAvailableExternal;
        mMemoryTotal = builder.mMemoryTotal;
        mMemoryFree = builder.mMemoryFree;
        mMemoryMax = builder.mMemoryMax;
        mSystemMemoryLow = builder.mSystemMemoryLow;
    }


    public long getAvailableExternal() {
        return mAvailableExternal;
    }

    public long getMemoryTotal() {
        return mMemoryTotal;
    }

    public long getMemoryFree() {
        return mMemoryFree;
    }

    public long getMemoryMax() {
        return mMemoryMax;
    }

    public long getSystemMemoryLow() {
        return mSystemMemoryLow;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Keys.MEMORY_ACAILABLE_EXTERNAL.getKey(), getAvailableExternal());
        jsonObject.put(Keys.MEMORY_TOTAL.getKey(), getMemoryTotal());
        jsonObject.put(Keys.MEMORY_FREE.getKey(), getMemoryFree());
        jsonObject.put(Keys.MEMORY_MAX.getKey(), getMemoryMax());
        jsonObject.put(Keys.SYSTEM_MEMORY_LOW.getKey(), getSystemMemoryLow());
        return jsonObject;
    }

    public static class Builder {

        private long mAvailableExternal;
        private long mMemoryTotal;
        private long mMemoryFree;
        private long mMemoryMax;
        private long mSystemMemoryLow;

        public Builder(MemoryManager manager) {
            mAvailableExternal = manager.getAvailableExternalMemorySizeMegaByte();
            mMemoryTotal = manager.getTotalMemoryMegaByte();
            mMemoryFree = manager.getFreeMemoryMegaByte();
            mMemoryMax = manager.getMaxMemoryMegaByte();
            mSystemMemoryLow = manager.getSystemLowMemory() ? 1 : 0;
        }


        public MemoryReport build() {
            return new MemoryReport(this);
        }
    }
}
