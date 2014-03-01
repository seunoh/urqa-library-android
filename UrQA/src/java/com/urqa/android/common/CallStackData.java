package com.urqa.android.common;

import org.json.JSONException;
import org.json.JSONObject;

public class CallStackData implements ToJson {
    private String mActivityName;
    private String mErrorName;
    private String mClassName;
    private int mLine;
    private String mCallStack;

    public String getActivityName() {
        return mActivityName;
    }

    public void setActivityName(String activityName) {
        mActivityName = activityName;
    }

    public String getErrorName() {
        return mErrorName;
    }

    public void setErrorName(String errorName) {
        mErrorName = errorName;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public int getLine() {
        return mLine;
    }

    public void setLine(int line) {
        mLine = line;
    }

    public String getCallStack() {
        return mCallStack;
    }

    public void setCallStack(String callStack) {
        mCallStack = callStack;
    }


    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lastactivity", getActivityName());
        jsonObject.put("errorname", getErrorName());
        jsonObject.put("errorclassname", getClassName());
        jsonObject.put("linenum", getLine());
        jsonObject.put("callstack", getCallStack());
        return jsonObject;
    }
}
