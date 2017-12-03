package com.a2017398956.nodesignmodeframework.activity;

import android.app.Activity;
import android.os.Bundle;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.apt.annotation.Repalce;

public class ReplacedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replaced);
    }

    @Repalce
    private void replace() {

    }

}
