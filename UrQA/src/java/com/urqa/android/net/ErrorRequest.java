package com.urqa.android.net;

import com.urqa.android.common.AndroidReport;
import com.urqa.android.common.ApplicationReport;
import com.urqa.android.common.CallStackReport;
import com.urqa.android.common.DisplayReport;
import com.urqa.android.common.MemoryReport;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class ErrorRequest extends Request {

    public ErrorRequest(Response.ResponseListener listener) {
        super(Method.POST, UrlFactory.create(UrlFactory.Url.EXCEPTION), listener);
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Context-Type", PROTOCOL_CONTENT_TYPE_JSON);
        return map;
    }


    public void setReport(ApplicationReport data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setReport(AndroidReport data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setReport(CallStackReport data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setReport(DisplayReport data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setReport(MemoryReport data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
           fail(e);
        }
    }

}
