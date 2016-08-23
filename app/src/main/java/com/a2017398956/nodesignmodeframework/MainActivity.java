package com.a2017398956.nodesignmodeframework;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.ViewDragHelper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.nfl.libraryoflibrary.utils.LogTool;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.utils.PhoneInfoTool;
import com.nfl.libraryoflibrary.utils.RootDetector;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;
import com.nfl.libraryoflibrary.view.CustomHorizontalLeftSlidingView;
import com.nfl.libraryoflibrary.view.CustomHorizontalLeftSlidingView2;
public class MainActivity extends BaseActivity {

    private LinearLayout ll_root_view ;
    private TextView tv_test_info ;
    private Button bn_result_exception ;
    private Button bn_recycler ;
    private Button bn_test ;
    private LinearLayout ll_left_sliding ;
    private LinearLayout ll_displayed ;
    private LinearLayout ll_hidden ;
    private TextView tv_01 , tv_02 , tv_03;
    private int tv_02_width , tv_03_width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView() ;
        setListenerss() ;
//        CustomBroadcastSender.sendAppStartBroadCast(this);
    }

    private void initView() {
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view) ;
        tv_test_info = (TextView) findViewById(R.id.tv_test_info);
        tv_test_info.setText("手机是否root：" + new RootDetector().isDeviceRooted());

        bn_result_exception = (Button) findViewById(R.id.bn_result_exception) ;
        bn_recycler = (Button) findViewById(R.id.bn_recycler) ;
        bn_test = (Button) findViewById(R.id.bn_test) ;
        ll_left_sliding = (LinearLayout) findViewById(R.id.ll_left_sliding) ;
        ll_displayed = (LinearLayout) findViewById(R.id.ll_displayed) ;
        ll_hidden = (LinearLayout) findViewById(R.id.ll_hidden) ;
        tv_01 = (TextView) findViewById(R.id.tv_01) ;
        tv_02 = (TextView) findViewById(R.id.tv_02) ;
        tv_03 = (TextView) findViewById(R.id.tv_03) ;
        tv_02_width = tv_02.getWidth() ;
        tv_03_width = tv_03.getWidth() ;
        LogTool.i("a:" + tv_03_width + " , b:" + tv_02_width + ",c:" + tv_01.getWidth());
        addLeftSlidingView();
    }

    private void setListenerss(){
        bn_result_exception.setOnClickListener(onClickListener);
        bn_recycler.setOnClickListener(onClickListener);
        bn_test.setOnClickListener(onClickListener);
        tv_02.setOnClickListener(onClickListener);
        ll_left_sliding.setOnTouchListener(onTouchListener);

    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                LogTool.i("ACTION_UP");
            }
            LogTool.i("ACTION_UP" + motionEvent.getAction()) ;
            return false;
        }
    } ;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null ;
            switch (view.getId()){
                case R.id.bn_result_exception:
                    intent = new Intent(MainActivity.this, ResultInfoExceptionActivity.class);
                    break;
                case R.id.bn_recycler:
                    break;
                case R.id.bn_test:
                    intent =  new Intent(MainActivity.this , TestActivity.class) ;
                    break;
                case R.id.ll_displayed :
                    ToastTool.showShortToast("ll_displayed");
                    break;
                case R.id.tv_01:
                    ToastTool.showShortToast("tv_01");
                    break;
                case R.id.tv_02:
                    ToastTool.showShortToast("tv_02");
                    break;
            }
            if(null != intent){
                startActivity(intent);
            }
        }
    } ;

    private void addLeftSlidingView(){
        LinearLayout ll01 = new LinearLayout(this) ;
        Button t01 = new Button(this) ;
        t01.setWidth(PhoneInfoTool.getScreenWidth(this));
        t01.setText("test");
        t01.setGravity(Gravity.CENTER);
        t01.setHeight(180);
        t01.setClickable(false);
//        t01.setBackgroundColor(Color.CYAN);
        ll01.addView(t01);

        LinearLayout ll02 = new LinearLayout(this) ;
        TextView t02 = new TextView(this) ;
        t02.setWidth(180);
        t02.setText("yes");
        t02.setHeight(180);
        t02.setClickable(true);
        t02.setGravity(Gravity.CENTER);
        t02.setBackgroundColor(Color.RED);
        ll02.addView(t02);
        CustomHorizontalLeftSlidingView2 customHorizontalLeftSlidingView2 = new CustomHorizontalLeftSlidingView2(this , ll01 , ll02) ;
        ll_root_view.addView(customHorizontalLeftSlidingView2.getView());
    }
}
