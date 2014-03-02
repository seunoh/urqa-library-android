package com.urqa.android.net;

import com.urqa.android.common.AndroidData;
import com.urqa.android.common.AppData;
import com.urqa.android.common.CallStackData;
import com.urqa.android.common.DisplayData;
import com.urqa.android.common.MemoryData;
import com.urqa.android.report.ErrorReportFactory;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seunoh on 2014. 2. 28..
 */
public class ErrorRequest extends Request {

    public ErrorRequest(Response.ResponseListener listener) {
        super(Method.POST, UrQAUrlFactory.create(UrQAUrlFactory.Url.EXCEPTION), listener);
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Context-Type", "application/json; charset=utf-8");
        return map;
    }


    public void setAndroidData(AndroidData data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setAppData(AppData data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setCallStackData(CallStackData data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setDisplayData(DisplayData data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
            fail(e);
        }
    }

    public void setMemoryData(MemoryData data) {
        try {
            addParams(data.toJson());
        } catch (JSONException e) {
           fail(e);
        }
    }

}
