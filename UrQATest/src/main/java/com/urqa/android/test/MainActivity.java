package com.urqa.android.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.urqa.android.UrQAHelper;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = tm.getSimCountryIso();
        String countryCode1 = tm.getNetworkCountryIso();


        Log.e("APP", "countryCode" + countryCode);
        Log.e("APP", "countryCode" + countryCode1);

        //UrQA.leaveBreadCrumb();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t = new Thread() {
                    @Override
                    public void run() {
                        int a = 100 / 0;
                    }
                };
                t.start();
                //Log.e("APP", String.valueOf(a));
                //UrQALog.sendException(new Exception("Exception1111"), "MainActivity", ErrorLevel.MINOR);
            }
        });


        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(UrQAHelper.getInstance().getVersion());
    }
}
