package com.urqa.android.eventpath;

import com.urqa.android.collector.DateCollector;
import com.urqa.android.common.EventPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author JeongSeungsu
 * @since 2013. 8. 12.오후 4:49:57
 */
public class EventPathManager {

    private static volatile EventPathManager sInstance;
    private List<EventPath> mEventPaths = new ArrayList<EventPath>();

    private static final int MAX_EVENT_PATH = 10;

    private EventPath mErrorEventPaths[] = new EventPath[MAX_EVENT_PATH];
    private int mEventCount = 0;


    private EventPathManager() {
    }


    public static EventPathManager getInstance() {
        if (sInstance == null)
            sInstance = new EventPathManager();
        return sInstance;
    }

    public synchronized void create(int step, String label) {
        List<StackTraceElement> stackTrace = Arrays.asList(new Exception().getStackTrace());

        EventPath.Builder builder = new EventPath.Builder(stackTrace.get(step));
        builder.setDateTime(DateCollector.getDate());
        builder.setLabel(label);


        EventPath eventPath = builder.build();

        shiftErrorEventPath();
        mErrorEventPaths[MAX_EVENT_PATH - 1] = eventPath;
        mEventCount++;
        mEventPaths.add(eventPath);
    }

    private void shiftErrorEventPath() {
        System.arraycopy(mErrorEventPaths, 1, mErrorEventPaths, 0, MAX_EVENT_PATH - 1);
    }

    public List<EventPath> getEventPath() {
        return mEventPaths;
    }

    public int getEventCount() {
        return mEventCount;
    }

    public List<EventPath> getErrorEventPath() {
        List<EventPath> eventPaths = new ArrayList<EventPath>();

        int maxCount = mEventCount;
        if (maxCount >= MAX_EVENT_PATH)
            maxCount = MAX_EVENT_PATH;

        eventPaths.addAll(Arrays.asList(mErrorEventPaths).subList(MAX_EVENT_PATH - maxCount, MAX_EVENT_PATH));

        return eventPaths;
    }

    public void clear() {
        getInstance().getEventPath().clear();
    }

    public static List<EventPath> getNumberofEventPath(int number) {
        final int size = getInstance().getEventPath().size();
        final int start = (size - number) < 0 ? 0 : (size - number);

        List<EventPath> eventPaths = new ArrayList<EventPath>();
        for (int i = start; i < size; i++) {
            eventPaths.add(getInstance().getEventPath().get(i));
        }

        return eventPaths;
    }

}
