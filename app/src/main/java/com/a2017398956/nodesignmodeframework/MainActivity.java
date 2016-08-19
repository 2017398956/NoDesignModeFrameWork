package com.a2017398956.nodesignmodeframework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CustomBroadcastSender.sendAppStartBroadCast(this);
    }

    public void next(View view){
        Intent intent = new Intent(this , ResultInfoExceptionActivity.class) ;
        startActivity(intent) ;
    }
}
