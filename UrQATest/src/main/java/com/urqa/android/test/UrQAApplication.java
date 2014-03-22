package com.urqa.android.test;

import android.app.Application;

import com.urqa.android.UrQA;

/**
 * @author seunoh on 2014. 3. 1..
 */
public class UrQAApplication extends Application {

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();


        UrQA.newSession(getApplicationContext(), "667285E8");
    }
}
