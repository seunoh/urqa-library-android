package com.urqa.android.net;

import android.util.Log;

import com.urqa.android.UrQAHelper;
import com.urqa.android.common.ErrorReport;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class SendErrorProcessURLConnection extends Thread {

    private ErrorReport mReport;
    private String mFileName;


    public SendErrorProcessURLConnection(ErrorReport report, String fileName) {
        this.mReport = report;
        this.mFileName = fileName;
    }

    @Override
    public void run() {
        UrQAHelper helper = UrQAHelper.getInstance();


        try {
            HttpClient client = new DefaultHttpClient();
            String nativeUrl = UrQAUrlFactory.create(UrQAUrlFactory.Url.NATIVE);
            HttpPost post = new HttpPost(nativeUrl);

            post.setHeader("Content-Type", "application/json; charset=utf-8");
            client.getParams().setParameter("http.protocol.expect-continue",
                    false);
            client.getParams().setParameter("http.connection.timeout", 5000);
            client.getParams().setParameter("http.socket.timeout", 5000);

//MapHelper.toMap(mReport.getAndroidData())
            JSONObject jsonObject = new JSONObject();

            String test = jsonObject.toString();
            StringEntity input = new StringEntity(test, "UTF-8");

            post.setEntity(input);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

            if (!helper.isTransferLog())
                return;

            String jsonData = "";
            try {
                jsonData = EntityUtils.toString(resEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                HttpClient logClient = new DefaultHttpClient();

                HttpPost logPost = new HttpPost(UrQAUrlFactory.create(UrQAUrlFactory.Url.LOG, ""));

                logClient.getParams().setParameter(
                        "http.protocol.expect-continue", false);
                logClient.getParams().setParameter("http.connection.timeout",
                        5000);
                logClient.getParams().setParameter("http.socket.timeout", 5000);

                // 1. 파일의 내용을 body 로 설정함
                logPost.setHeader("Content-Type", "text/plain; charset=utf-8");
                StringEntity entity = new StringEntity(mReport.getLogData(), "UTF-8");
                logPost.setEntity(entity);

                logClient.execute(logPost);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //dump 보내기
        try {
            HttpClient dumpClient = new DefaultHttpClient();

            HttpPost dumpPost = new HttpPost(UrQAUrlFactory.create(UrQAUrlFactory.Url.DUMP, "instance"));

            dumpClient.getParams().setParameter(
                    "http.protocol.expect-continue", false);
            dumpClient.getParams()
                    .setParameter("http.connection.timeout", 5000);
            dumpClient.getParams().setParameter("http.socket.timeout", 5000);

            // 1. 파일의 내용을 body 로 설정함
            dumpPost.setHeader("Content-Type",
                    "multipart/form-data; charset=utf-8");
            File file = new File(mFileName);
            FileEntity entity = new FileEntity(file, "multipart/form-data");
            dumpPost.setEntity(entity);

            if (file.exists()) {
                Log.d("URQATest", "File True");
            }

            dumpClient.execute(dumpPost);
            file.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
