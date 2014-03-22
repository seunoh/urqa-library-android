package com.urqa.android.tests;

import com.urqa.android.UrQA;
import com.urqa.android.common.AuthReport;

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

        AuthReport authReport = new AuthReport();
        authReport.setApiKey("59BB0235");
        authReport.setAppVersion("1.0");

        mLatch.await();

    }

    public void testNewSession() throws Exception {
        UrQA.newSession(getActivity(), "59BB0235");

    }
}
