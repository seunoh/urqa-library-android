package com.urqa.android;

import android.content.Context;
import android.util.Log;

import com.urqa.android.collector.AppManager;
import com.urqa.android.common.Auth;
import com.urqa.android.common.ExceptionHandler;
import com.urqa.android.eventpath.EventPathManager;
import com.urqa.android.net.ErrorNativeRequest;
import com.urqa.android.net.HttpRunnable;
import com.urqa.android.net.Request;
import com.urqa.android.net.Response;
import com.urqa.android.net.UrQAUrlFactory;

import org.json.JSONObject;

import java.security.InvalidKeyException;

/**
 * @author seunoh on 2014. 1. 25..
 */

public final class UrQA {

    public static final String TAG = "UrQA-Android";

    public synchronized static void newSession(Context context, String apiKey) {
        if (context == null)
            throw new IllegalArgumentException("Context is null");

        if (apiKey == null || "".equals(apiKey))
            throw new IllegalArgumentException("ApiKey must not be empty: " + apiKey);

        if (!UrQAHelper.getInstance().isTwice()) {
            UrQAHelper.init(context);
            ExceptionHandler.createInstance();
            start(context, apiKey);
        } else {
            Log.w(TAG, new IllegalAccessException("Method is called twice"));
        }
    }


    private synchronized static void start(Context context, String apiKey) {

        final Auth auth = new Auth();
        auth.setApiKey(apiKey);
        auth.setAppVersion(new AppManager(context).getAppVersionName());

        Request request = new Request(Request.Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.CONNECT), new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {
                String session = response.optString("idsession");
                if (!"".equals(session)) {
                    Log.e(TAG, auth.toJson().toString());
                    UrQAHelper.getInstance().put(UrQAHelper.Keys.AUTH, auth.toJson().toString());
                    UrQAHelper.getInstance().put(UrQAHelper.Keys.SESSION, session);
                } else {
                    errorResponse(new InvalidKeyException("apiKey"));
                }

            }

            @Override
            public void errorResponse(Exception e) {
                Log.e(TAG, e.toString());
            }
        });

        request.addParams(auth.toJson());
        HttpRunnable.start(request);
        EventPathManager.getInstance().clear();
    }


    public synchronized static void leaveBreadCrumb() {
        EventPathManager.getInstance().create(2, "");
    }

    public synchronized static void leaveBreadCrumb(String tag) {
        EventPathManager.getInstance().create(2, tag);
    }

    public static void nativeCrashCallback(String str) {

        // TODO errorNativeRequest
        //ErrorReport report = ErrorReportFactory.createNative(UrQAHelper.getInstance().getContext());

        ErrorNativeRequest errorNativeRequest = new ErrorNativeRequest(str);
        errorNativeRequest.start();

        try {
            errorNativeRequest.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void setLogCat(boolean toggleLogCat) {
        UrQAHelper.getInstance().setToggleLogCat(toggleLogCat);
    }

    public static void setLogging(int logLine) {
        UrQAHelper.getInstance().setTransferLog(true);
        UrQAHelper.getInstance().setLogLine(logLine);
    }

    public static void setLogging(int logLine, String logFilter) {
        UrQAHelper.getInstance().setTransferLog(true);
        UrQAHelper.getInstance().setLogLine(logLine);
        UrQAHelper.getInstance().setLogFilter(logFilter);
    }
}
