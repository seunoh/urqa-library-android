package com.urqa.android.net;

/**
 * @author seunoh on 2014. 1. 26..
 */
public final class LogRequest extends Request {


    public LogRequest(Response.ResponseListener listener, String... url) {
        super(Method.POST, UrlFactory.create(UrlFactory.Url.LOG, url), listener);
    }

//                    StringEntity entity = new StringEntity(ReportFactory.createLog(), "UTF-8");


    @Override
    public byte[] getBody() {
        try {
            return getParams().get("log").getBytes(PROTOCOL_CHARSET);
        } catch (Exception e) {
            fail(e);
            return null;
        }
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE_TEXT;
    }
}
