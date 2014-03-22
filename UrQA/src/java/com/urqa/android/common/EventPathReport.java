package com.urqa.android.common;

import org.json.JSONException;
import org.json.JSONObject;

public class EventPathReport implements ToJson {

    private final String mDateTime;

    private final String mEventClassName;

    private final String mEventMethodName;

    private final int mEventLine;

    private final String mLabel;

    public EventPathReport(Builder builder) {
        mDateTime = builder.mDateTime;
        mEventClassName = builder.mEventClassName;
        mEventMethodName = builder.mEventMethodName;
        mEventLine = builder.mEventLine;
        mLabel = builder.mLabel;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getEventClassName() {
        return mEventClassName;
    }

    public String getEventMethodName() {
        return mEventMethodName;
    }

    public int getEventLine() {
        return mEventLine;
    }

    public String getLabel() {
        return mLabel;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Keys.EVENT_DATE.getKey(), getDateTime());
        jsonObject.put(Keys.EVENT_CLASS_NAME.getKey(), getEventClassName());
        jsonObject.put(Keys.EVENT_METHOD_NAME.getKey(), getEventMethodName());
        jsonObject.put(Keys.EVENT_LINE.getKey(), getEventLine());
        jsonObject.put(Keys.EVENT_LABEL.getKey(), getLabel());
        return jsonObject;
    }

    public static class Builder {
        private String mDateTime;
        private String mEventClassName;
        private String mEventMethodName;
        private int mEventLine;
        private String mLabel;

        public Builder(StackTraceElement element) {
            setEventClassName(element.getClassName());
            setEventMethodName(element.getMethodName());
            setEventLine(element.getLineNumber());
        }

        public Builder setDateTime(String dateTime) {
            mDateTime = dateTime;
            return this;
        }

        public Builder setEventClassName(String eventClassName) {
            mEventClassName = eventClassName;
            return this;
        }

        public Builder setEventMethodName(String eventMethodName) {
            mEventMethodName = eventMethodName;
            return this;
        }


        public Builder setEventLine(int eventLine) {
            mEventLine = eventLine;
            return this;
        }

        public Builder setLabel(String label) {
            mLabel = label;
            return this;
        }


        public EventPathReport build() {
            return new EventPathReport(this);
        }
    }
}
