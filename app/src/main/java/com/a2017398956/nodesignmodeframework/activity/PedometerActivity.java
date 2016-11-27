package com.a2017398956.nodesignmodeframework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.pedometer.StepsCountTool;
import com.nfl.libraryoflibrary.view.BaseActivity;
import com.nfl.libraryoflibrary.view.db_insight.DBInsightService;

import java.util.Timer;
import java.util.TimerTask;

public class PedometerActivity extends BaseActivity {

    private TextView tv_steps;
    private Button bn_database_manager;
    private Timer timer;
    private TimerTask timmerTask;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                tv_steps.setText("今天的步数：" + StepsCountTool.getTodaySteps());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        initView();
        initData();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.schedule(timmerTask, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void initView() {
        tv_steps = (TextView) findViewById(R.id.tv_steps);
        bn_database_manager = (Button) findViewById(R.id.bn_database_manager);
        bn_database_manager.setText(DBInsightService.canShowDBInsight ? "关闭数据库管理工具" : "开启数据库管理工具");
    }

    private void initData() {
        timer = new Timer();
        timmerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(100);
            }
        };
    }

    private void setListeners() {
        bn_database_manager.setOnClickListener(onClickListener);
    }

    private CustomOnClickListener onClickListener = new CustomOnClickListener() {
        @Override
        public void onClick(View v) {
            super.onClick(v);
            if (v.getId() == R.id.bn_database_manager) {
                DBInsightService.canShowDBInsight = !DBInsightService.canShowDBInsight;
                bn_database_manager.setText(DBInsightService.canShowDBInsight ? "关闭数据库管理工具" : "开启数据库管理工具");
                startService(new Intent(PedometerActivity.this, DBInsightService.class));
            }
        }
    };

}
