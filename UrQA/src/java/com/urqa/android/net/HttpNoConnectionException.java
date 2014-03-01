package com.urqa.android.net;

/**
 * Created by seunoh on 2014. 1. 26..
 */
public class HttpNoConnectionException extends Exception {

    public HttpNoConnectionException() {
    }

    public HttpNoConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public HttpNoConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpNoConnectionException(Throwable throwable) {
        super(throwable);
    }
}
