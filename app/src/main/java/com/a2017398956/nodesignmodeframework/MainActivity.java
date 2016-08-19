package com.a2017398956.nodesignmodeframework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.utils.RootDetector;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView tv_test_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView() ;
//        CustomBroadcastSender.sendAppStartBroadCast(this);
    }

    private void initView() {
        tv_test_info = (TextView) findViewById(R.id.tv_test_info);
        tv_test_info.setText("手机是否root：" + new RootDetector().isDeviceRooted());
    }

    public void next(View view) {
        Intent intent = new Intent(this, ResultInfoExceptionActivity.class);
        startActivity(intent);
    }
}
