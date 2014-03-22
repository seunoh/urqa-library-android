package com.urqa.clientinterface;

import android.content.Context;

import com.urqa.android.UrQA;
import com.urqa.android.UrQALog;
import com.urqa.android.level.ErrorLevel;
import com.urqa.rank.ErrorRank;


/**
 * @deprecated Use {@link com.urqa.android.UrQA} instead.
 */
public final class URQAController {


    /**
     * @deprecated Use {@link com.urqa.android.UrQA#leaveBreadCrumb()} instead.
     */
    public static void leaveBreadcrumb() {
        UrQA.leaveBreadCrumb();
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQA#leaveBreadCrumb(String)} instead.
     */
    public static void leaveBreadcrumb(String tag) {
        UrQA.leaveBreadCrumb(tag);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQA#nativeCrashCallback(String)} instead.
     */
    public static int NativeCrashCallback(String str) {
        UrQA.nativeCrashCallback(str);
        return 0;
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQA#newSession(android.content.Context, String)} instead.
     */
    public static void InitializeAndStartSession(Context context, String APIKEY) {
        UrQA.newSession(context, APIKEY);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#sendException(Exception, String, com.urqa.android.level.ErrorLevel)} instead.
     */
    public static void SendException(Exception e, String Tag, ErrorRank rank) {
        UrQALog.sendException(e, Tag, ErrorLevel.valueOf(rank));
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#sendException(Exception)} instead.
     */
    public static void SendException(Exception e) {
        UrQALog.sendException(e);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#sendException(Exception, String)} instead.
     */
    public static void SendException(Exception e, String Tag) {
        UrQALog.sendException(e, Tag);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQA#setSendLog(boolean)} instead.
     */
    public static void SetLogCat(boolean toggleLog) {
        UrQA.setSendLog(toggleLog);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQA#setLogging(int, String)} instead.
     */
    public static void SetLogging(int Line, String Filter) {
        UrQA.setLogging(Line, Filter);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQA#setLogging(int)} instead.
     */
    public static void SetLogging(int Line) {
        UrQA.setLogging(Line);
    }


    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int v(String tag, String Msg, Throwable tr) {
        return UrQALog.v(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int v(String tag, String Msg) {
        return UrQALog.v(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int d(String tag, String Msg, Throwable tr) {
        return UrQALog.d(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int d(String tag, String Msg) {
        return UrQALog.d(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int i(String tag, String Msg, Throwable tr) {
        return UrQALog.i(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int i(String tag, String Msg) {
        return UrQALog.i(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int w(String tag, String Msg, Throwable tr) {
        return UrQALog.w(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int w(String tag, String Msg) {
        return UrQALog.w(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int e(String tag, String Msg, Throwable tr) {
        return UrQALog.e(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.android.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int e(String tag, String Msg) {
        return UrQALog.v(tag, Msg);
    }


    /**
     * use Android NDK
     *
     * @return android cache path
     * @deprecated Use {@link com.urqa.android.UrQA#getCacheDirPath()} instead.
     */
    public static String GetCachePath() {
        return UrQA.getCacheDirPath();
    }
}