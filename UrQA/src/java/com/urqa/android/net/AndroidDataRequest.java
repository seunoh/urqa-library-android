package com.urqa.android.net;

import com.urqa.android.common.AndroidData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class AndroidDataRequest extends Request {

    public AndroidDataRequest(Response.ResponseListener listener) {
        super(Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.EXCEPTION), listener);
    }


    public AndroidDataRequest(AndroidData data, Response.ResponseListener listener) {
        super(Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.EXCEPTION), listener);
        setAndroidData(data);
    }


    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Context-Type", "application/json; charset=utf-8");
        return map;
    }


    public void setAndroidData(AndroidData data) {
    }
}
