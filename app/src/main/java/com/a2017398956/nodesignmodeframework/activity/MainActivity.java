package com.a2017398956.nodesignmodeframework.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.a2017398956.nodesignmodeframework.databinding.ActivityMainBinding;
import com.a2017398956.nodesignmodeframework.databinding.MainActivityHanding;
import com.nfl.libraryoflibrary.constant.ApplicationContext;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.PhoneInfoTool;
import com.nfl.libraryoflibrary.utils.RootDetectorTool;
import com.nfl.libraryoflibrary.view.BaseActivity;
import com.nfl.libraryoflibrary.view.CustomHorizontalLeftSlidingView2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityHanding mainActivityHanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(null);
        hiddenBackIcon();
        setActionBarTitle("主页");
        initDataBinding();
        addAnotherLeftSlidingView();
        setListeners();
        printRootInfo();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, ll_data_binding, true);
        ll_data_binding.setVisibility(View.VISIBLE);
        mainActivityHanding = new MainActivityHanding(this, binding);
        binding.setMainActivityHanding(mainActivityHanding);
    }

    private void setListeners() {
        binding.llLeftSliding.setOnTouchListener(onTouchListener);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                LogTool.i("ACTION_UP");
            }
            LogTool.i("ACTION_UP" + motionEvent.getAction());
            return false;
        }
    };

    private void addAnotherLeftSlidingView() {
        LinearLayout ll01 = new LinearLayout(this);
        Button t01 = new Button(this);
        t01.setWidth(PhoneInfoTool.getScreenWidth(this));
        t01.setText("test");
        t01.setGravity(Gravity.CENTER);
        t01.setHeight(180);
        t01.setClickable(false);
        ll01.addView(t01);

        LinearLayout ll02 = new LinearLayout(this);
        TextView t02 = new TextView(this);
        t02.setWidth(180);
        t02.setText("yes");
        t02.setHeight(180);
        t02.setClickable(true);
        t02.setGravity(Gravity.CENTER);
        t02.setBackgroundColor(Color.RED);
        ll02.addView(t02);
        CustomHorizontalLeftSlidingView2 customHorizontalLeftSlidingView2 = new CustomHorizontalLeftSlidingView2(this, ll01, ll02);
        binding.llRootView.addView(customHorizontalLeftSlidingView2.getView());
    }

    private void printRootInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> filterInfos = new ArrayList<>();// 判断是否有 root 可能的关键词
                filterInfos.add("root");
                String rootDetailInfo = new RootDetectorTool(ApplicationContext.applicationContext, filterInfos).getRootDetailInfo();
                Looper.prepare();
                binding.tvTestInfo.setText(rootDetailInfo);
                Looper.loop();
            }
        }).start();
    }
}
