package com.urqa.android.net;

import android.util.Log;

import com.urqa.android.UrQAHelper;
import com.urqa.android.common.AndroidData;
import com.urqa.android.common.ErrorReport;
import com.urqa.android.common.EventPath;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SendErrorProcess extends Thread {
    private ErrorReport mErrorReport;
    private AndroidData mAndroidData;
    private String mLogData;

    public SendErrorProcess(ErrorReport report) {
        this.mErrorReport = report;
    }

    public SendErrorProcess(AndroidData androidData, String logData) {
        mAndroidData = androidData;
        mLogData = logData;
    }

    @Override
    public void run() {
        Log.e(SendErrorProcess.class.getName(), "run");
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(UrQAUrlFactory.create(UrQAUrlFactory.Url.EXCEPTION));

            post.setHeader("Content-Type", "application/json; charset=utf-8");
            client.getParams().setParameter("http.protocol.expect-continue", false);
            client.getParams().setParameter("http.connection.timeout", 5000);
            client.getParams().setParameter("http.socket.timeout", 5000);

            AndroidData data = mErrorReport.getAndroidData();

//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("sdkversion", data.getSdkVersion());
//            jsonObject.put("locale", data.getLocale());
//            jsonObject.put("tag", data.getTag());
//            jsonObject.put("rank", data.getRank());
//            jsonObject.put("callstack", data.getCallStack());
//            jsonObject.put("apikey", data.getApiKey());
//            jsonObject.put("datetime", data.getDateTime());
//            jsonObject.put("device", data.getDevice());
//            jsonObject.put("country", data.getCountry());
//            jsonObject.put("errorname", data.getErrorName());
//            jsonObject.put("errorclassname", data.getErrorClassName());
//            jsonObject.put("linenum", data.getLineNum());
//            jsonObject.put("appversion", data.getAppVersion());
//            jsonObject.put("osversion", data.getOSVersion());
//            jsonObject.put("gpson", data.getGpsOn());
//            jsonObject.put("wifion", data.getWifiOn());
//            jsonObject.put("mobileon", data.getMobileOn());
//            jsonObject.put("scrwidth", data.getScrWidth());
//            jsonObject.put("scrheight", data.getScrHeight());
//            jsonObject.put("batterylevel", data.getBatteryLevel());
//            jsonObject.put("availsdcard", data.getAvailSdCard());
//            jsonObject.put("rooted", data.getRooted());
//            jsonObject.put("appmemtotal", data.getAppMemTotal());
//            jsonObject.put("appmemfree", data.getAppMemFree());
//            jsonObject.put("appmemmax", data.getAppMemMax());
//            jsonObject.put("kernelversion", data.getKernelVersion());
//            jsonObject.put("xdpi", data.getXdpi());
//            jsonObject.put("ydpi", data.getYdpi());
//            jsonObject.put("scrorientation", data.getSysMemLow());
//            jsonObject.put("sysmemlow", data.getSysMemLow());
//            jsonObject.put("lastactivity", data.getLastActivity());
//            ////////////////Event Path 계산////////////////////
//            JSONArray eventpath = new JSONArray();
//
//
//            for (EventPath path : data.getEventPaths()) {
//                JSONObject event = new JSONObject();
//
//                event.put("datetime", path.getDateTime());
//                event.put("classname", path.getEventClassName());
//                event.put("methodname", path.getEventMethodName());
//                event.put("label", path.getLabel());
//                event.put("linenum", path.getEventLine());
//
//                eventpath.put(event);
//            }
//            jsonObject.put("eventpaths", eventpath);


            //String test = jsonObject.toString();
            String test = "";
            post.setEntity(new StringEntity(test, "UTF-8"));
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            if (responsePOST.getStatusLine().getStatusCode() >= 200) {
                String entity = EntityUtils.toString(resEntity);

                Log.e(SendErrorProcess.class.getName(), entity);
            }


            Log.e(SendErrorProcess.class.getName(), UrQAHelper.getInstance().isTransferLog() + "");
            if (!UrQAHelper.getInstance().isTransferLog())
                return;

            try {
                String entity = EntityUtils.toString(resEntity);
                JSONObject json = new JSONObject(entity);
                Log.e(SendErrorProcess.class.getName(), json.toString());


                UrQAHelper.getInstance().put(UrQAHelper.Keys.INSTANCE, json.optString("idinstance"));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                HttpClient httpClient = new DefaultHttpClient();


                HttpPost logpost = new HttpPost(UrQAUrlFactory.create(UrQAUrlFactory.Url.LOG, UrQAHelper.getInstance().get(UrQAHelper.Keys.INSTANCE)));

                httpClient.getParams().setParameter("http.protocol.expect-continue", false);
                httpClient.getParams().setParameter("http.connection.timeout", 5000);
                httpClient.getParams().setParameter("http.socket.timeout", 5000);

                // 1. 파일의 내용을 body 로 설정함
                logpost.setHeader("Content-Type", "text/plain; charset=utf-8");
                StringEntity entity = new StringEntity(mErrorReport.getLogData(), "UTF-8");
                logpost.setEntity(entity);

                httpClient.execute(logpost);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}