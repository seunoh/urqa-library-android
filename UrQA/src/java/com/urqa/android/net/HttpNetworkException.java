package com.urqa.android.net;

/**
 * Created by seunoh on 2014. 1. 26..
 */
public class HttpNetworkException extends Exception {

    public HttpNetworkException() {
    }

    public HttpNetworkException(String detailMessage) {
        super(detailMessage);
    }

    public HttpNetworkException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpNetworkException(Throwable throwable) {
        super(throwable);
    }

    public HttpNetworkException(NetworkResponse networkResponse) {

    }
}
