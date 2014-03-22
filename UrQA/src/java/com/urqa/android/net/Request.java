package com.urqa.android.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author seunoh on 2014. 1. 26..
 */
public class Request {

    /**
     * Default encoding for POST or PUT parameters. See {@link #getParamsEncoding()}.
     */
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    /**
     * Charset for request.
     */
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    protected static final String PROTOCOL_CONTENT_TYPE_JSON =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    protected static final String PROTOCOL_CONTENT_TYPE_TEXT =
            String.format("text/plain; charset=%s", PROTOCOL_CHARSET);


    private static final int TIMEOUT_MS = 5000;
    private final int mMethod;
    private final String mUrl;
    private Map<String, String> mHeader;
    private Map<String, String> mParams;
    private Response.ResponseListener mListener;

    public Request(int method, String url, Response.ResponseListener listener) {
        this.mMethod = method;
        this.mUrl = url;
        this.mListener = listener;


        mParams = new HashMap<String, String>();
        mHeader = new HashMap<String, String>();
    }

    /**
     * Return the method for this request.  Can be one of the values in {@link Method}.
     */
    public int getMethod() {
        return mMethod;
    }

    /**
     * Returns the URL of this request.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the socket timeout in milliseconds per retry attempt. (This value can be changed
     * per retry attempt if a backoff is specified via backoffTimeout()). If there are no retry
     * attempts remaining, this will cause delivery of a {@link java.io.IOException} error.
     */
    public int getTimeoutMs() {
        return TIMEOUT_MS;
    }

    public void setHeader(Map<String, String> header) {
        if (header != null)
            mHeader = header;
        else
            mHeader = Collections.emptyMap();
    }

    public void addParams(String key, String value) {
        if (mParams == null)
            mParams = new HashMap<String, String>();

        mParams.put(key, value);
    }

    public void addParams(JSONObject jsonObject) {
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            addParams(key, jsonObject.optString(key));
        }

    }

    public void success(JSONObject s) {
        if (mListener != null)
            mListener.response(s);
    }

    public void fail(Exception e) {
        if (mListener != null)
            mListener.errorResponse(e);
    }

    public void finish() {
        if (mListener != null)
            mListener.finish();
    }
    public Map<String, String> getHeaders() {
        return mHeader;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE_JSON;
    }

    /**
     * Returns the raw POST or PUT body to be sent.
     */
    public byte[] getBody() {
        Map<String, String> params = getParams();
        try {
            if (params != null && params.size() > 0) {

                JSONObject jsonObject = new JSONObject(params);

                return jsonObject.toString().getBytes(PROTOCOL_CHARSET);

            } else {
                throw new IllegalArgumentException("Parameter empty");
            }
        } catch (Exception e) {
            fail(e);
            return null;
        }

    }

    public Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, getParamsEncoding());
            return Response.success(new JSONObject(jsonString));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new Exception(e));
        } catch (JSONException je) {
            return Response.error(new Exception(je));
        }

    }

    /**
     * Supported request methods.
     */
    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }
}
