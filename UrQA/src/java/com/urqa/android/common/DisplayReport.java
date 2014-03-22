package com.urqa.android.common;

import com.urqa.android.collector.DisplayManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class DisplayReport implements ToJson {

    private final int mWidth;
    private final int mHeight;
    private final float mXDpi;
    private final float mYDpi;
    private final int mOrientation;


    public DisplayReport(Builder builder) {
        mWidth = builder.mWidth;
        mHeight = builder.mHeight;
        mXDpi = builder.mXDpi;
        mYDpi = builder.mYDpi;
        mOrientation = builder.mOrientation;
    }


    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public float getXDpi() {
        return mXDpi;
    }

    public float getYDpi() {
        return mYDpi;
    }

    public int getOrientation() {
        return mOrientation;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Keys.DISPLAY_WIDTH.getKey(), getWidth());
        jsonObject.put(Keys.DISPLAY_HEIGHT.getKey(), getHeight());
        jsonObject.put(Keys.DISPLAY_XDPI.getKey(), getXDpi());
        jsonObject.put(Keys.DISPLAY_YDPI.getKey(), getYDpi());
        jsonObject.put(Keys.DISPLAY_ORIENTATION.getKey(), getOrientation());
        return jsonObject;
    }

    public static class Builder {
        private int mWidth;
        private int mHeight;
        private float mXDpi;
        private float mYDpi;
        private int mOrientation;

        public Builder(DisplayManager manager) {
            this.mWidth = manager.getWidthPixels();
            this.mHeight = manager.getHeightPixels();
            this.mXDpi = manager.getXDpi();
            this.mYDpi = manager.getYDpi();
            this.mOrientation = manager.getOrientation();
        }


        public DisplayReport build() {
            return new DisplayReport(this);
        }
    }


}
