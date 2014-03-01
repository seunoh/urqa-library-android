package com.urqa.android.tests;

import android.util.Log;

import com.urqa.android.UrQA;
import com.urqa.android.UrQAHelper;
import com.urqa.android.common.Auth;
import com.urqa.android.net.HttpRunnable;
import com.urqa.android.net.Request;
import com.urqa.android.net.Response;
import com.urqa.android.net.UrQAUrlFactory;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

/**
 * @author seunoh on 2014. 1. 26..
 */
public class HttpTestCase extends UrQATestCase {

    private CountDownLatch mLatch;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLatch = new CountDownLatch(1);
    }


    public void testNetworkThread() throws Exception {


        Request request = new Request(Request.Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.CONNECT), new Response.ResponseListener() {
            @Override
            public void response(JSONObject jsonObject) {

                //UrQAHelper.getInstance().put("idsession", jsonObject.optString("idsession"));

                Log.e(HttpTestCase.class.getName(), jsonObject.toString());
                assertNotNull(jsonObject.toString());
                assertEquals("".equals(jsonObject.toString()), false);
                mLatch.countDown();
            }

            @Override
            public void errorResponse(Exception e) {
                assertNotNull(e);
                mLatch.countDown();
            }
        });

        Auth auth = new Auth();
        auth.setApiKey("59BB0235");
        auth.setAppVersion("1.0");

//        request.addParams(MapHelper.toMap(auth));
        HttpRunnable.start(request);


        mLatch.await();

    }

    public void testNewSession() throws Exception {
        UrQA.newSession(getActivity(), "59BB0235");

    }
}
