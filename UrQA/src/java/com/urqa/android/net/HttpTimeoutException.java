package com.urqa.android.net;

/**
 * Created by seunoh on 2014. 1. 26..
 */
public class HttpTimeoutException extends Exception {

    public HttpTimeoutException() {
    }

    public HttpTimeoutException(String detailMessage) {
        super(detailMessage);
    }

    public HttpTimeoutException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpTimeoutException(Throwable throwable) {
        super(throwable);
    }

}
