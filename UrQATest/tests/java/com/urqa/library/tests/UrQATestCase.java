package com.urqa.library.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.urqa.library.test.MainActivity;

/**
 * @author seunoh on 2014. 2. 27..
 */
public class UrQATestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    public UrQATestCase() {
        super(MainActivity.class);
    }
}
