package com.urqa.android.net;

import android.content.Context;
import android.util.Log;

import com.urqa.android.UrQAHelper;
import com.urqa.android.collector.LogCollector;
import com.urqa.android.common.AndroidReport;
import com.urqa.android.common.ApplicationReport;
import com.urqa.android.common.CallStackReport;
import com.urqa.android.common.DisplayReport;
import com.urqa.android.common.MemoryReport;
import com.urqa.android.common.ReportFactory;
import com.urqa.android.level.ErrorLevel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author seunoh on 2014. 1. 26..
 */
public final class LogSend {

    public static void send(Context context, Exception e, String tag, ErrorLevel level) {
        ApplicationReport applicationReport = ReportFactory.createApplication(tag, level);
        AndroidReport appData = ReportFactory.createAndroid(context);
        CallStackReport callStackReport = ReportFactory.createCallStack(e);
        DisplayReport displayReport = ReportFactory.createDisplay(context);
        MemoryReport memoryReport = ReportFactory.createMemory(context);


        ErrorRequest request = new ErrorRequest(new Response.ResponseListener() {
            @Override
            public void response(JSONObject response) {
                LogRequest logRequest = new LogRequest(new Response.ResponseListener() {
                    @Override
                    public void response(JSONObject response) {
                        Log.e("APP", LogCollector.getLog());
                        Log.e("APP", response.toString());
                    }

                    @Override
                    public void errorResponse(Exception e) {
                        Log.e("APP", LogCollector.getLog());
                        Log.e("APP", e.getMessage());
                    }

                    @Override
                    public void finish() {

                    }
                }, UrQAHelper.getInstance().get(UrQAHelper.Keys.INSTANCE));
                logRequest.addParams("log", LogCollector.getLog());
                RequestExecutor.execute(logRequest);
            }

            @Override
            public void errorResponse(Exception e) {
                Log.e("APP", LogCollector.getLog());
                Log.e("APP", "aaaaaa");
            }

            @Override
            public void finish() {

            }
        });





        try {
            String auth = UrQAHelper.getInstance().get(UrQAHelper.Keys.AUTH);
            if (auth != null && !"".equals(auth))
                request.addParams(new JSONObject(auth));

            request.setReport(applicationReport);
            request.setReport(appData);
            request.setReport(callStackReport);
            request.setReport(displayReport);
            request.setReport(memoryReport);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        RequestExecutor.execute(request);

    }


}
