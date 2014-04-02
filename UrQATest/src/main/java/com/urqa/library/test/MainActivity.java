package com.urqa.library.test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.urqa.android.test.R;
import com.urqa.library.UrQA;
import com.urqa.library.UrQAExceptionHandler;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int a = 100 / 0;
                    }
                });
            }
        });


        UrQAExceptionHandler.createInstance(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.d("AAA", "aaaaaaaaaaaaaa");
            }
        });


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UrQALog.sendException(new Exception("Error"), "MainActivity", ErrorLevel.MINOR);

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        new Handler(Looper.myLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                int a = 100 / 0;
                            }
                        });
                    }
                };

                thread.start();

            }
        });

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(UrQA.VERSION);
    }
}
