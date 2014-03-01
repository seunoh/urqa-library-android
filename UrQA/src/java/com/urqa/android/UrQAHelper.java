package com.urqa.android;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import com.urqa.android.common.ExceptionHandler;

import java.net.SocketException;
import java.util.HashMap;

/**
 * @author seunoh on 2014. 1. 25..
 */

public final class UrQAHelper extends HashMap<UrQAHelper.Keys, String> {


    private static volatile UrQAHelper sInstance;

    private Context mContext;
    private boolean mToggleLogCat;
    private int mLogLine;
    private boolean mTransferLog;
    private String mLogFilter;


    private boolean mTwice;


    public static void init(Context context) {
        getInstance().mContext = context;
        getInstance().mTwice = true;
    }


    public static UrQAHelper getInstance() {
        if (sInstance == null)
            sInstance = new UrQAHelper();
        return sInstance;
    }



    public Context getContext() {
        if (mContext == null)
            throw new IllegalArgumentException("called method UrQA.newSession()");

        return mContext;
    }

    public boolean isTwice() {
        return mTwice;
    }

    private boolean checkPermission(Context context) {
        final int res = context.checkCallingOrSelfPermission(Manifest.permission.INTERNET);

        if (res != PackageManager.PERMISSION_GRANTED) {
            UrQALog.w(UrQA.TAG, "", new SocketException("Permission denied (maybe missing INTERNET permission)"));
            return false;
        } else {
            return true;
        }
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

    public final String getUrQAVersion() {
        return "0.92";
    }


    public enum Keys {
        AUTH, API_KEY, APP_VERSION, SESSION, INSTANCE
    }
}
