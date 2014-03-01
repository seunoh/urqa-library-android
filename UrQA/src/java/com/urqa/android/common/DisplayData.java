package com.urqa.android.common;

import com.urqa.android.collector.DisplayManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class DisplayData implements ToJson {

    private final int mScrWidth;
    private final int mScrHeight;
    private final float mXDpi;
    private final float mYDpi;
    private final int mScrOrientation;


    public DisplayData(Builder builder) {
        mScrWidth = builder.mScrWidth;
        mScrHeight = builder.mScrHeight;
        mXDpi = builder.mXDpi;
        mYDpi = builder.mYDpi;
        mScrOrientation = builder.mScrOrientation;
    }


    public int getScrWidth() {
        return mScrWidth;
    }

    public int getScrHeight() {
        return mScrHeight;
    }

    public float getXDpi() {
        return mXDpi;
    }

    public float getYDpi() {
        return mYDpi;
    }

    public int getScrOrientation() {
        return mScrOrientation;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scrwidth", getScrWidth());
        jsonObject.put("scrheight", getScrHeight());
        jsonObject.put("xdpi", getXDpi());
        jsonObject.put("ydpi", getYDpi());
        jsonObject.put("scrorientation", getScrOrientation());
        return jsonObject;
    }

    public static class Builder {
        private int mScrWidth;
        private int mScrHeight;
        private float mXDpi;
        private float mYDpi;
        private int mScrOrientation;

        public Builder(DisplayManager manager) {
            this.mScrWidth = manager.getWidthPixels();
            this.mScrHeight = manager.getHeightPixels();
            this.mXDpi = manager.getXDpi();
            this.mYDpi = manager.getYDpi();
            this.mScrOrientation = manager.getOrientation();
        }


        public DisplayData build() {
            return new DisplayData(this);
        }
    }


}
