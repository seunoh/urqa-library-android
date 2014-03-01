package com.urqa.android.net;

/**
 * Created by seunoh on 2014. 1. 26..
 */
public class HttpServerException extends Exception {

    public HttpServerException() {
    }

    public HttpServerException(String detailMessage) {
        super(detailMessage);
    }

    public HttpServerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpServerException(Throwable throwable) {
        super(throwable);
    }

    public HttpServerException(NetworkResponse networkResponse) {

    }
}
