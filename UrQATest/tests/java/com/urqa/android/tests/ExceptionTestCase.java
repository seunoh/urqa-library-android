package com.urqa.android.tests;

import android.test.AndroidTestCase;

import com.urqa.android.UrQA;
import com.urqa.android.UrQAHelper;
import com.urqa.android.UrQALog;
import com.urqa.android.common.Auth;
import com.urqa.android.rank.ErrorLevel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author seunoh on 2014. 2. 27..
 */
public class ExceptionTestCase extends UrQATestCase {



    private CountDownLatch mLatch;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLatch = new CountDownLatch(1);

        Auth auth = new Auth();
        auth.setApiKey("59BB0235");
        auth.setAppVersion("1.0");

        UrQAHelper.getInstance().put("idsession", "1393499332666");
    }


    public void testException() throws Exception {

        UrQALog.e(UrQA.TAG, "testException");


        UrQALog.sendException(getActivity(), new Exception(), "Test1", ErrorLevel.CRITICAL);

        mLatch.await(5, TimeUnit.SECONDS);



    }
}
