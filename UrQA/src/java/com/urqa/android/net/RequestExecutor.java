package com.urqa.android.net;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

/**
 * @author seunoh on 2014. 1. 26..
 */
public final class RequestExecutor {

    private static RequestExecutor sInstance;

    /**
     * Use {@link RequestExecutor#execute(Request)}
     */

    private RequestExecutor() {

    }


    private static RequestExecutor getInstance() {
        if (sInstance == null)
            sInstance = new RequestExecutor();
        return sInstance;
    }


    public static synchronized void execute(Request request) {
        getInstance().executeRequest(request);
    }


    private void executeRequest(Request request) {
        new Processing(request).execute();
    }

    private static class Processing extends AsyncTask<Void, Void, Response> {

        private Request mRequest;

        private Processing(Request request) {
            mRequest = request;
        }

        @Override
        protected Response doInBackground(Void... params) {
            Response response = null;
            response = Response.error(new Exception());
            return response;
        }


        @Override
        protected void onPostExecute(Response response) {
            final ResponseExecutor responseExecutor = new ResponseExecutor(new Handler(Looper.getMainLooper()));
            responseExecutor.postResponse(mRequest, response);
        }
    }
}
