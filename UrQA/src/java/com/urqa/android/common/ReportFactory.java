package com.urqa.android.common;

import android.content.Context;

import com.urqa.android.collector.AppManager;
import com.urqa.android.collector.CallStackCollector;
import com.urqa.android.collector.DisplayManager;
import com.urqa.android.collector.LogCollector;
import com.urqa.android.collector.MemoryManager;
import com.urqa.android.common.AndroidReport;
import com.urqa.android.common.ApplicationReport;
import com.urqa.android.common.CallStackReport;
import com.urqa.android.common.DisplayReport;
import com.urqa.android.common.MemoryReport;
import com.urqa.android.level.ErrorLevel;

public class ReportFactory {

    public static ApplicationReport createApplication(String tag, ErrorLevel level) {
        return new ApplicationReport.Builder(tag, level).build();
    }

    public static MemoryReport createMemory(Context context) {
        return new MemoryReport.Builder(new MemoryManager(context)).build();
    }

    public static DisplayReport createDisplay(Context context) {
        return new DisplayReport.Builder(new DisplayManager(context)).build();
    }

    public static AndroidReport createAndroid(Context context) {
        return new AndroidReport.Builder(new AppManager(context)).build();
    }

    public static CallStackReport createCallStack(Throwable throwable) {
        return CallStackCollector.parseStackTrace(throwable);
    }
}
