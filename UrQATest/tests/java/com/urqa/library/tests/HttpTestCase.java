package com.urqa.library.tests;

import com.urqa.library.UrQA;
import com.urqa.library.common.Auth;

import java.util.concurrent.CountDownLatch;

/**
 * @author seunoh on 2014. 1. 26..
 */
public class HttpTestCase extends UrQATestCase {

    private CountDownLatch mLatch;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLatch = new CountDownLatch(1);
    }


    public void testNetworkThread() throws Exception {

        Auth auth = new Auth();
        auth.setApiKey("59BB0235");
        auth.setAppVersion("1.0");

        mLatch.await();

    }

    public void testNewSession() throws Exception {
        UrQA.newSession(getActivity(), "59BB0235");

    }
}
