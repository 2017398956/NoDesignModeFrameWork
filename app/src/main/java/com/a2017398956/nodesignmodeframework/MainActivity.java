package com.a2017398956.nodesignmodeframework;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.utils.PhoneInfoTool;
import com.nfl.libraryoflibrary.utils.RootDetector;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;
import com.nfl.libraryoflibrary.view.CustomHorizontalLeftSlidingView;

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
        CustomHorizontalLeftSlidingView customHorizontalLeftSlidingView ;
        TextView tv_01 = new TextView(this) ;
        tv_01.setWidth(PhoneInfoTool.getScreenHeight(this));
        tv_01.setText("tv_01");
        tv_01.setTextColor(Color.WHITE);
        tv_01.setBackgroundColor(Color.YELLOW);
        TextView tv_02 = new TextView(this) ;
        tv_02.setText("tv_02");
        tv_02.setTextColor(Color.WHITE);
        tv_02.setBackgroundColor(Color.RED);
//        customHorizontalLeftSlidingView = new CustomHorizontalLeftSlidingView(this , tv_01 , tv_02 ) ;
//        ((RelativeLayout) tv_test_info.getParent()).addView(customHorizontalLeftSlidingView);
    }

    public void next(View view) {
        Intent intent = new Intent(this, ResultInfoExceptionActivity.class);
        startActivity(intent);
    }
}
