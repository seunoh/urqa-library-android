package com.urqa.android;

import android.os.Looper;
import android.util.Log;

import com.urqa.android.common.AndroidReport;
import com.urqa.android.common.ApplicationReport;
import com.urqa.android.common.CallStackReport;
import com.urqa.android.common.DisplayReport;
import com.urqa.android.common.MemoryReport;
import com.urqa.android.common.ReportFactory;
import com.urqa.android.level.ErrorLevel;
import com.urqa.android.net.ErrorRequest;
import com.urqa.android.net.RequestExecutor;
import com.urqa.android.net.Response;

import org.json.JSONObject;

public class UrQAExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private UrQAExceptionHandler() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    public static UrQAExceptionHandler createInstance() {
        return new UrQAExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        sendException(thread, throwable);



    }

    private void sendException(final Thread thread, final Throwable throwable) {
        ErrorRequest request = new ErrorRequest(new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {
                Log.e("TAG", response.toString());
            }

            @Override
            public void errorResponse(Exception e) {
                Log.e("TAG", e.getMessage());

            }

            @Override
            public void finish() {
                exitApplication(thread, throwable);
            }
        });

        ApplicationReport applicationReport = ReportFactory.createApplication(UrQA.TAG, ErrorLevel.UN_HANDLE);
        AndroidReport appData = ReportFactory.createAndroid(UrQAHelper.getContext());
        CallStackReport callStackReport = ReportFactory.createCallStack(throwable);
        DisplayReport displayReport = ReportFactory.createDisplay(UrQAHelper.getContext());
        MemoryReport memoryReport = ReportFactory.createMemory(UrQAHelper.getContext());

        request.setReport(applicationReport);
        request.setReport(appData);
        request.setReport(callStackReport);
        request.setReport(displayReport);
        request.setReport(memoryReport);
        RequestExecutor.execute(request);

    }

    private void exitApplication(Thread thread, Throwable throwable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mUncaughtExceptionHandler.uncaughtException(thread, throwable);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }

    }
}
