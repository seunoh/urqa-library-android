package com.urqa.android.common;

import com.urqa.android.UrQA;
import com.urqa.android.UrQAHelper;
import com.urqa.android.net.HttpRunnable;
import com.urqa.android.net.Request;
import com.urqa.android.net.Response;
import com.urqa.android.net.UrQAUrlFactory;
import com.urqa.android.rank.ErrorLevel;
import com.urqa.android.report.ErrorReportFactory;

import org.json.JSONObject;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private ExceptionHandler() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    public static void createInstance() {
        new ExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        ErrorReport report = ErrorReportFactory.create(UrQAHelper.getInstance().getContext(), throwable, UrQA.TAG, ErrorLevel.UN_HANDLE);

        Request request = new Request(Request.Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.EXCEPTION), new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {

            }

            @Override
            public void errorResponse(Exception e) {

            }
        });


        //request.addParams(report);
        HttpRunnable.start(request);

        mUncaughtExceptionHandler.uncaughtException(thread, throwable);

        exitApplication();
    }

    private void exitApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
