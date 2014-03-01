package com.urqa.android.net;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by seunoh on 2014. 1. 26..
 */
public class BasicNetwork {

    private HttpProcess mHttp;
    protected final ByteArrayPool mPool;


    public BasicNetwork(HttpProcess http) {
        mHttp = http;
        mPool = new ByteArrayPool(2096);
    }

    public NetworkResponse performRequest(Request request) throws Exception {

        HttpResponse httpResponse = null;
        byte[] responseContents = null;
        Map<String, String> responseHeaders = new HashMap<String, String>();
        try {
            httpResponse = mHttp.performRequest(request);

            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            responseHeaders = convertHeaders(httpResponse.getAllHeaders());
            // Handle cache validation.
            if (statusCode == HttpStatus.SC_NOT_MODIFIED) {
                return new NetworkResponse(HttpStatus.SC_NOT_MODIFIED,
                        null, responseHeaders, true);
            }


            // Some responses such as 204s do not have content.  We must check.
            if (httpResponse.getEntity() != null) {
                responseContents = entityToBytes(httpResponse.getEntity());
            } else {
                // Add 0 byte response as a way of honestly representing a
                // no-content request.
                responseContents = new byte[0];
            }


            if (statusCode < 200 || statusCode > 299) {
                throw new IOException();
            }


            return new NetworkResponse(statusCode, responseContents, responseHeaders, false);
        } catch (SocketTimeoutException e) {
            throw new HttpTimeoutException("socket");
        } catch (ConnectTimeoutException e) {
            throw new HttpTimeoutException("connection");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Bad URL " + request.getUrl(), e);
        } catch (IOException e) {
            int statusCode;
            NetworkResponse networkResponse;
            if (httpResponse != null) {
                statusCode = httpResponse.getStatusLine().getStatusCode();
            } else {
                throw new HttpNoConnectionException(e);
            }

            if (responseContents != null) {
                networkResponse = new NetworkResponse(statusCode, responseContents, responseHeaders, false);
                throw new HttpServerException(networkResponse);
            } else {
                throw new HttpNetworkException("NetworkError");
            }
        }
    }

    private byte[] entityToBytes(HttpEntity entity) throws IOException, HttpServerException {
        PoolingByteArrayOutputStream bytes =
                new PoolingByteArrayOutputStream(mPool, (int) entity.getContentLength());
        byte[] buffer = null;
        try {
            InputStream in = entity.getContent();
            if (in == null) {
                throw new HttpServerException();
            }
            buffer = mPool.getBuf(1024);
            int count;
            while ((count = in.read(buffer)) != -1) {
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } finally {
            try {
                // Close the InputStream and release the resources by "consuming the content".
                entity.consumeContent();
            } catch (IOException e) {
                // This can happen if there was an exception above that left the entity in
                // an invalid state.
                Log.e(getClass().getName(), "Error occured when calling consumingContent");
            }
            mPool.returnBuf(buffer);
            bytes.close();
        }
    }

    /**
     * Converts Headers[] to Map<String, String>.
     */
    private Map<String, String> convertHeaders(Header[] headers) {
        Map<String, String> result = new HashMap<String, String>();
        for (Header header : headers) {
            result.put(header.getName(), header.getValue());
        }
        return result;
    }
}
