package com.urqa.android.net;

/**
 * @author seunoh on 2014. 03. 22..
 */
public class AuthRequest extends Request {

    public AuthRequest(Response.ResponseListener listener) {
        super(Method.POST, UrlFactory.create(UrlFactory.Url.CONNECT), listener);
    }
}