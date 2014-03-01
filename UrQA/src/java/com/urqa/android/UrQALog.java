package com.urqa.android;

import android.content.Context;
import android.util.Log;

import com.urqa.android.common.AndroidData;
import com.urqa.android.common.AppData;
import com.urqa.android.common.CallStackData;
import com.urqa.android.common.DisplayData;
import com.urqa.android.common.ErrorReport;
import com.urqa.android.common.MemoryData;
import com.urqa.android.net.AndroidDataRequest;
import com.urqa.android.net.HttpRunnable;
import com.urqa.android.net.Response;
import com.urqa.android.net.SendErrorProcess;
import com.urqa.android.net.UrQAUrlFactory;
import com.urqa.android.rank.ErrorLevel;
import com.urqa.android.report.ErrorReportFactory;
import com.urqa.rank.ErrorRank;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 1. 26..
 */
public final class UrQALog {

    public static final String TAG = UrQA.TAG;


    public static int v(String tag, String msg, Throwable tr) {
        return println(Log.VERBOSE, tag, msg, tr);
    }

    public static int v(String tag, String msg) {
        return println(Log.VERBOSE, tag, msg, null);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return println(Log.DEBUG, tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        return println(Log.DEBUG, tag, msg, null);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return println(Log.INFO, tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        return println(Log.INFO, tag, msg, null);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return println(Log.WARN, tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        return println(Log.WARN, tag, msg, null);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return println(Log.ERROR, tag, msg, tr);
    }

    public static int e(String tag, String msg) {
        return println(Log.ERROR, tag, msg, null);
    }


    private static int println(int priority, String tag, String msg, Throwable throwable) {

        if (throwable == null)
            return Log.println(priority, tag, msg);
        else
            return printlnThrowable(priority, tag, msg, throwable);
    }


    private static int printlnThrowable(int priority, String tag, String msg, Throwable throwable) {
        switch (priority) {
            case Log.VERBOSE:
                return Log.v(tag, msg, throwable);
            case Log.DEBUG:
                return Log.d(tag, msg, throwable);
            case Log.INFO:
                return Log.i(tag, msg, throwable);
            case Log.WARN:
                return Log.w(tag, msg, throwable);
            case Log.ERROR:
                return Log.e(tag, msg, throwable);
            default:
                return Log.e(tag, msg, throwable);
        }
    }

    public static void sendException(Exception e) {
        sendException(e, TAG, ErrorLevel.CRITICAL);
    }

    public static void sendException(Exception e, String tag) {
        sendException(e, tag, ErrorLevel.CRITICAL);
    }

    public static void sendException(Exception e, String tag, ErrorLevel level) {
        requestSendException(UrQAHelper.getInstance().getContext(), e, tag, level);
    }

    public static void sendException(Exception e, String Tag, ErrorRank rank) {
        requestSendException(UrQAHelper.getInstance().getContext(), e, Tag, ErrorLevel.valueOf(rank));
    }


    private static void requestSendException(Context context, Exception e, String tag, ErrorLevel level) {
        ErrorReport report = ErrorReportFactory.create(context, e, tag, level);
        AndroidData androidData = ErrorReportFactory.createAndroidData(tag, level);
        AppData appData = ErrorReportFactory.createAppData(context);
        CallStackData callStackData = ErrorReportFactory.createCallStack(e);
        DisplayData displayData = ErrorReportFactory.createDisplayData(context);
        MemoryData memoryData = ErrorReportFactory.createMemoryData(context);


        AndroidDataRequest request = new AndroidDataRequest(new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {
                Log.e(TAG + 2, response.toString());

                try {
                    HttpClient logclient = new DefaultHttpClient();

                    HttpPost logpost = new HttpPost(UrQAUrlFactory.create(UrQAUrlFactory.Url.LOG, UrQAHelper.getInstance().get(UrQAHelper.Keys.INSTANCE)));
                    logclient.getParams().setParameter("http.protocol.expect-continue", false);
                    logclient.getParams().setParameter("http.connection.timeout", 5000);
                    logclient.getParams().setParameter("http.socket.timeout", 5000);

                    // 1. 파일의 내용을 body 로 설정함
                    logpost.setHeader("Content-Type", "text/plain; charset=utf-8");
                    StringEntity entity = new StringEntity(ErrorReportFactory.createLogData(UrQAHelper.getInstance().getContext()), "UTF-8");
                    logpost.setEntity(entity);


                    HttpResponse httpResponse = logclient.execute(logpost);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void errorResponse(Exception e) {

               Log.e(TAG+2, e.toString());


            }
        });


        try {
            String auth = UrQAHelper.getInstance().get(UrQAHelper.Keys.AUTH);
            if (auth != null && !"".equals(auth))
                request.addParams(new JSONObject(auth));

            request.addParams(androidData.toJson());
            request.addParams(appData.toJson());
            request.addParams(callStackData.toJson());
            request.addParams(displayData.toJson());
            request.addParams(memoryData.toJson());
            request.addParams(androidData.toJson());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        HttpRunnable.start(request);

        SendErrorProcess errorProcess = new SendErrorProcess(report);
        //errorProcess.start();
    }


}
