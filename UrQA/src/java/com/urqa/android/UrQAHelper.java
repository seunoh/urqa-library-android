package com.urqa.android;

import android.content.Context;

import java.util.HashMap;

/**
 * @author seunoh on 2014. 1. 25..
 */

public final class UrQAHelper extends HashMap<UrQAHelper.Keys, String> {


    private static volatile UrQAHelper sInstance;

    private static Context sContext;
    private boolean mToggleLogCat;
    private int mLogLine;
    private boolean mTransferLog;
    private String mLogFilter;


    private boolean mTwice;


    public static void init(Context context) {
        UrQAHelper.sContext = context;
        getInstance().mTwice = true;
    }


    public static UrQAHelper getInstance() {
        if (sInstance == null)
            sInstance = new UrQAHelper();
        return sInstance;
    }


    public static Context getContext() {
        if (sContext == null)
            throw new IllegalArgumentException("called method UrQA.newSession()");
        else
            return sContext;
    }

    public boolean isTwice() {
        return mTwice;
    }

    public boolean isToggleLogCat() {
        return mToggleLogCat;
    }

    public void setToggleLogCat(boolean toggleLogCat) {
        mToggleLogCat = toggleLogCat;
    }

    public int getLogLine() {
        return mLogLine;
    }

    public void setLogLine(int logLine) {
        mLogLine = logLine;
    }

    public boolean isTransferLog() {
        return mTransferLog;
    }

    public void setTransferLog(boolean transferLog) {
        mTransferLog = transferLog;
    }

    public String getLogFilter() {
        return mLogFilter;
    }

    public void setLogFilter(String logFilter) {
        mLogFilter = logFilter;
    }

    public final String getName() {
        return "UrQA-Android";
    }

    public final String getVersion() {
        return "0.92";
    }


    public enum Keys {
        AUTH, API_KEY, APP_VERSION, SESSION, INSTANCE
    }
}
