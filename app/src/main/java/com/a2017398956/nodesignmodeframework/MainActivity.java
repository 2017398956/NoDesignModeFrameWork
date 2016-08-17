package com.a2017398956.nodesignmodeframework;

import android.os.Bundle;

import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomBroadcastSender.sendAppStartBroadCast(this);
    }
}
