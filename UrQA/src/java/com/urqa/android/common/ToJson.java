package com.urqa.android.common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 2. 28..
 */
public interface ToJson {

    public JSONObject toJson() throws JSONException;

    /**
     * @author seunoh on 2014. 1. 26..
     */
    enum Keys {
        SESSION("idsession"), //

        GPS("gpson"), //
        WIFI("wifion"), //
        MOBILE("mobileon"), //
        BATTERY_LEVEL("batterylevel"), //
        SUPER_USER("rooted"), //
        KERNEL_VERSION("kernelversion"), //
        LOCALE("locale"), //
        COUNTRY("country"), //

        UrQA_VERSION("sdkversion"), //
        TAG("tag"), //
        ERROR_LEVEL("rank"), //
        DATE("datetime"), //
        DEVICE("device"), //
        OS_VERSION("osversion"), //
        EVENT_PATH("eventpaths"), //


        API_KEY("apikey"), //
        APP_VERSION("appversion"), //


        ERROR_ACTIVITY("lastactivity"), //
        ERROR_NAME("errorname"), //
        ERROR_CLASS_NAME("errorclassname"), //
        ERROR_LINE("linenum"), //
        ERROR_CALL_STACK("callstack"), //


        DISPLAY_WIDTH("scrwidth"), //
        DISPLAY_HEIGHT("scrheight"), //
        DISPLAY_XDPI("xdpi"), //
        DISPLAY_YDPI("ydpi"), //
        DISPLAY_ORIENTATION("scrorientation"), //



        EVENT_DATE("datetime"), //
        EVENT_CLASS_NAME("classname"), //
        EVENT_METHOD_NAME("methodname"), //
        EVENT_LINE("linenum"), //
        EVENT_LABEL("label"), //


        MEMORY_ACAILABLE_EXTERNAL("availsdcard"), //
        MEMORY_TOTAL("appmemtotal"), //
        MEMORY_FREE("appmemfree"), //
        MEMORY_MAX("appmemmax"), //
        SYSTEM_MEMORY_LOW("sysmemlow"), //
        ;

        private final String mKey;

        private Keys(String key) {
            this.mKey = key;

        }

        public String getKey() {
            return mKey;
        }
    }
}
