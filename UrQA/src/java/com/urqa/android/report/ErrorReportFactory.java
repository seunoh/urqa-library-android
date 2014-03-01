package com.urqa.android.report;

import android.content.Context;

import com.urqa.android.collector.AppManager;
import com.urqa.android.collector.CallStackCollector;
import com.urqa.android.collector.DisplayManager;
import com.urqa.android.collector.LogCollector;
import com.urqa.android.collector.MemoryManager;
import com.urqa.android.common.AndroidData;
import com.urqa.android.common.AppData;
import com.urqa.android.common.CallStackData;
import com.urqa.android.common.DisplayData;
import com.urqa.android.common.ErrorReport;
import com.urqa.android.common.MemoryData;
import com.urqa.android.rank.ErrorLevel;

public class ErrorReportFactory {

    public static ErrorReport create(Context context, Throwable throwable, String tag, ErrorLevel level) {
        ErrorReport report = new ErrorReport();
        report.setAndroidData(createAndroidData(context, throwable, tag, level));
        report.setLogData(createLogData(context));
        return report;
    }


    public static AndroidData createAndroidData(Context context, Throwable throwable, String tag, ErrorLevel level) {
        return new AndroidData.Builder(tag, level).build();
    }

    public static AndroidData createAndroidData(String tag, ErrorLevel level) {
        return new AndroidData.Builder(tag, level).build();
    }

    public static MemoryData createMemoryData(Context context) {
        return new MemoryData.Builder(new MemoryManager(context)).build();
    }

    public static DisplayData createDisplayData(Context context) {
        return new DisplayData.Builder(new DisplayManager(context)).build();
    }

    public static AppData createAppData(Context context) {
        return new AppData.Builder(new AppManager(context)).build();
    }

    public static CallStackData createCallStack(Throwable throwable) {
        return CallStackCollector.parseStackTrace(throwable);
    }

    public static String createLogData(Context context) {
        return LogCollector.getLog(context);
    }


    public static ErrorReport createNative(Context context) {
        //TODO native
        ErrorReport report = new ErrorReport();
        //report.setAndroidData(buildAndroidData(context, null, "", ErrorLevel.NATIVE));
        report.setLogData(LogCollector.getLog(context));
        return report;
    }
}
