package com.urqa.android.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.urqa.android.UrQA;
import com.urqa.android.UrQALog;
import com.urqa.android.rank.ErrorLevel;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UrQA.leaveBreadCrumb();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrQALog.sendException(new Exception("Exception1111"), "MainActivity", ErrorLevel.MINOR);
            }
        });


    }
}
