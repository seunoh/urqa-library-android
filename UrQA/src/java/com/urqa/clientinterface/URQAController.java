package com.urqa.clientinterface;

import android.content.Context;

import com.urqa.library.UrQA;
import com.urqa.library.UrQAHelper;
import com.urqa.library.UrQALog;
import com.urqa.library.UrQASetting;
import com.urqa.rank.ErrorRank;

/**
 * @deprecated Use {@link com.urqa.library.UrQA} instead.
 */
public final class URQAController {

    /**
     * @deprecated Use {@link com.urqa.library.UrQA#leaveBreadCrumb()} instead.
     */
    public static void leaveBreadcrumb() {
        UrQA.leaveBreadCrumb();
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQA#leaveBreadCrumb(String)} instead.
     */
    public static void leaveBreadcrumb(String tag) {
        UrQA.leaveBreadCrumb(tag);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQA#nativeCrashCallback(String)} instead.
     */
    public static int NativeCrashCallback(String str) {
        UrQA.nativeCrashCallback(str);
        return 0;
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQA#newSession(android.content.Context, String)} instead.
     */
    public static void InitializeAndStartSession(Context context, String APIKEY) {
        UrQA.newSession(context, APIKEY);
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#sendException(Exception, String, com.urqa.library.level.ErrorLevel)} instead.
     */
    public static void SendException(Exception e, String Tag, ErrorRank rank) {
        UrQALog.sendException(e, Tag, rank);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#sendException(Exception)} instead.
     */
    public static void SendException(Exception e) {
        UrQALog.sendException(e);
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#sendException(Exception, String)} instead.
     */
    public static void SendException(Exception e, String Tag) {
        UrQALog.sendException(e, Tag);
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQASetting#setToggleLogCat(boolean)} instead.
     */
    public static void SetLogCat(boolean toggleLog) {
        UrQASetting setting = new UrQASetting();
        setting.setToggleLogCat(toggleLog);
        UrQAHelper.getInstance().setSetting(setting);
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQASetting#setLogLine(int)}
     * and {@link com.urqa.library.UrQASetting#setLogFilter(String)} instead.
     */
    public static void SetLogging(int Line, String Filter) {
        UrQASetting setting = new UrQASetting();
        setting.setTransferLog(true);
        setting.setLogLine(Line);
        setting.setLogFilter(Filter);
        UrQAHelper.getInstance().setSetting(setting);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQASetting#setLogLine(int)} instead.
     */
    public static void SetLogging(int Line) {
        UrQASetting setting = new UrQASetting();
        setting.setTransferLog(true);
        setting.setLogLine(Line);
        UrQAHelper.getInstance().setSetting(setting);
    }


    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int v(String tag, String Msg, Throwable tr) {
        return UrQALog.v(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#v(String, String, Throwable)} instead.
     */
    public static int v(String tag, String Msg) {
        return UrQALog.v(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#d(String, String, Throwable)}  instead.
     */
    public static int d(String tag, String Msg, Throwable tr) {
        return UrQALog.d(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#d(String, String)} instead.
     */
    public static int d(String tag, String Msg) {
        return UrQALog.d(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#i(String, String, Throwable)} instead.
     */
    public static int i(String tag, String Msg, Throwable tr) {
        return UrQALog.i(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#i(String, String)} instead.
     */
    public static int i(String tag, String Msg) {
        return UrQALog.i(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#w(String, String, Throwable)} instead.
     */
    public static int w(String tag, String Msg, Throwable tr) {
        return UrQALog.w(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#w(String, String)} instead.
     */
    public static int w(String tag, String Msg) {
        return UrQALog.w(tag, Msg);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#e(String, String, Throwable)} instead.
     */
    public static int e(String tag, String Msg, Throwable tr) {
        return UrQALog.e(tag, Msg, tr);
    }

    /**
     * @deprecated Use {@link com.urqa.library.UrQALog#e(String, String)} instead.
     */
    public static int e(String tag, String Msg) {
        return UrQALog.e(tag, Msg);
    }


    /**
     * @deprecated Use {@link android.util.Log)} instead.
     */
    enum LogLevel {
        Verbose, Debug, Info, Warning, Error
    }

    private static String GetCachePath() {
        return UrQA.getCacheDirPath();
    }
}
