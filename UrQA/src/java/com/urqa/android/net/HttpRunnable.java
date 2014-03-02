package com.urqa.android.net;

import android.os.Handler;
import android.os.Looper;

import java.util.Queue;

/**
 * @author seunoh on 2014. 1. 26..
 */
public final class HttpRunnable {

    public static final String THREAD_NAME = "UrQA-Android-Http";


    private static int INDEX = 0;

    /**
     * Use {@link HttpRunnable#start(Request)}
     */

    private HttpRunnable() {

    }


    public static void start(Request request) {
        Thread thread = new Thread(new Processing(request));
        thread.setName(THREAD_NAME + " " + INDEX++);
        thread.start();
    }


    private static class Processing implements Runnable {
        private ResponseExecutor mResponseExecutor;
        private Request mRequest;

        private Processing(Request request) {
            mRequest = request;
            mResponseExecutor = new ResponseExecutor(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void run() {
            try {
                BasicNetwork network = new BasicNetwork(new HttpProcess());
                NetworkResponse networkResponse = network.performRequest(mRequest);
                Response response = mRequest.parseNetworkResponse(networkResponse);
                mResponseExecutor.postResponse(mRequest, response);
            } catch (Exception e) {
                mResponseExecutor.postError(mRequest, e);
            }
        }

    }


}
