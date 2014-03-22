package com.urqa.android;

import android.content.Context;
import android.util.Log;

import com.urqa.android.collector.AppManager;
import com.urqa.android.common.AuthReport;
import com.urqa.android.common.ToJson;
import com.urqa.android.eventpath.EventPathManager;
import com.urqa.android.net.AuthRequest;
import com.urqa.android.net.Request;
import com.urqa.android.net.ErrorNativeRequest;
import com.urqa.android.net.RequestExecutor;
import com.urqa.android.net.Response;
import com.urqa.android.net.UrlFactory;

import org.json.JSONObject;

import java.io.File;
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
            UrQAExceptionHandler.createInstance();
            EventPathManager.getInstance().clear();
            start(context, apiKey);
        } else {
            Log.w(TAG, new IllegalAccessException("Method is called twice"));
        }
    }


    private synchronized static void start(Context context, String apiKey) {

        final AuthReport authReport = new AuthReport();
        authReport.setApiKey(apiKey);
        authReport.setAppVersion(new AppManager(context).getAppVersionName());

        Request request = new Request(Request.Method.POST, UrlFactory.create(UrlFactory.Url.CONNECT), new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {
                String session = response.optString(ToJson.Keys.SESSION.getKey());
                Log.e("APP", session);
                if (!"".equals(session)) {
                    UrQAHelper.getInstance().put(UrQAHelper.Keys.AUTH, authReport.toJson().toString());
                    UrQAHelper.getInstance().put(UrQAHelper.Keys.SESSION, session);
                } else {
                    errorResponse(new InvalidKeyException("apiKey"));
                }

            }

            @Override
            public void errorResponse(Exception e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void finish() {

            }
        });

        request.addParams(authReport.toJson());

        RequestExecutor.execute(request);

    }


    public synchronized static void leaveBreadCrumb() {
        EventPathManager.getInstance().create(2, "");
    }

    public synchronized static void leaveBreadCrumb(String tag) {
        EventPathManager.getInstance().create(2, tag);
    }

    public static void nativeCrashCallback(String str) {
        // TODO errorNativeRequest
        //ErrorReport report = ReportFactory.createNative(UrQAHelper.getInstance().getContext());

        ErrorNativeRequest errorNativeRequest = new ErrorNativeRequest(str);
        errorNativeRequest.start();

        try {
            errorNativeRequest.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void setSendLog(boolean toggleLogCat) {
        UrQAHelper.getInstance().setToggleLogCat(toggleLogCat);
    }

    public static void setLogging(int line) {
        UrQAHelper.getInstance().setTransferLog(true);
        UrQAHelper.getInstance().setLogLine(line);
    }

    public static void setLogging(int line, String filter) {
        UrQAHelper.getInstance().setTransferLog(true);
        UrQAHelper.getInstance().setLogLine(line);
        UrQAHelper.getInstance().setLogFilter(filter);
    }

    /**
     * use Android NDK
     * @return android cache path
     */
    public static String getCacheDirPath() {
        File file = UrQAHelper.getContext().getCacheDir();
        return file != null ? file.getAbsolutePath() : null;
    }
}
