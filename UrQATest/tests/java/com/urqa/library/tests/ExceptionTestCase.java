package com.urqa.library.tests;

import android.util.Log;

import com.urqa.library.UrQA;
import com.urqa.library.UrQAHelper;
import com.urqa.library.common.AndroidReport;
import com.urqa.library.common.ApplicationReport;
import com.urqa.library.common.Auth;
import com.urqa.library.common.CallStackReport;
import com.urqa.library.common.DisplayReport;
import com.urqa.library.common.MemoryReport;
import com.urqa.library.common.ReportFactory;
import com.urqa.library.level.ErrorLevel;
import com.urqa.library.net.RequestExecutor;
import com.urqa.library.net.ResponseAdapter;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

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
        auth.setApiKey("667285E8");
        auth.setAppVersion("1.0");

        UrQAHelper.getInstance().put(UrQAHelper.Keys.API_KEY, "1393499332666");
    }


    public void testException() throws Exception {
        ErrorRequest request = new ErrorRequest(new ResponseAdapter() {
            @Override
            public void response(JSONObject response) {
                Log.e("AA", response.toString());
            }

            @Override
            public void errorResponse(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void finish() {
                mLatch.countDown();
            }
        });


        ApplicationReport applicationReport = ReportFactory.createApplication("UNIT_TEST", ErrorLevel.CRITICAL);
        AndroidReport androidReport = ReportFactory.createAndroid(getActivity().getApplication());
        CallStackReport callStackReport = ReportFactory.createCallStack(new Exception());
        DisplayReport displayReport = ReportFactory.createDisplay(getActivity().getApplication());
        MemoryReport memoryReport = ReportFactory.createMemory(getActivity().getApplication());

        request.addParams(UrQAHelper.getInstance().getAuth());
        request.addParams(applicationReport);
        request.addParams(androidReport);
        request.addParams(callStackReport);
        request.addParams(displayReport);
        request.addParams(memoryReport);


        Log.d(UrQA.TAG, request.getParamToJson().toString());

        RequestExecutor.execute(request);
        mLatch.await();

    }
}
