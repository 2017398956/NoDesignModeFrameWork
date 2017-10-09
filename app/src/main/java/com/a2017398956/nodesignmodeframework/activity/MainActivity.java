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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.a2017398956.nodesignmodeframework.databinding.ActivityMainBinding;
import com.a2017398956.nodesignmodeframework.databinding.MainActivityHanding;
import com.nfl.libraryoflibrary.constant.ApplicationContext;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.PhoneInfoTool;
import com.nfl.libraryoflibrary.utils.RootDetectorTool;
<<<<<<< HEAD
=======
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.utils.image.ImageLoadTool;
>>>>>>> d90c67ae3a6af1614e0f013acad935c4686bbea7
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

<<<<<<< HEAD
    private void initDataBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, ll_data_binding, true);
        ll_data_binding.setVisibility(View.VISIBLE);
        mainActivityHanding = new MainActivityHanding(this, binding);
        binding.setMainActivityHanding(mainActivityHanding);
=======
    private void initView() {
        sv = (ScrollView) findViewById(R.id.sv) ;

        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view) ;
        tv_test_info = (TextView) findViewById(R.id.tv_test_info);

        bn_result_exception = (Button) findViewById(R.id.bn_result_exception) ;
        bn_test = (Button) findViewById(R.id.bn_test) ;
        ll_left_sliding = (LinearLayout) findViewById(R.id.ll_left_sliding) ;
        ll_displayed = (LinearLayout) findViewById(R.id.ll_displayed) ;
        ll_hidden = (LinearLayout) findViewById(R.id.ll_hidden) ;
        tv_01 = (TextView) findViewById(R.id.tv_01) ;
        tv_02 = (TextView) findViewById(R.id.tv_02) ;
        tv_03 = (TextView) findViewById(R.id.tv_03) ;
        tv_02_width = tv_02.getWidth() ;
        tv_03_width = tv_03.getWidth() ;
        bn_bottom_sheet_behavior = (Button) findViewById(R.id.bn_bottom_sheet_behavior) ;
        bn_ptrsml = (Button) findViewById(R.id.bn_ptrsml) ;
        LogTool.i("a:" + tv_03_width + " , b:" + tv_02_width + ",c:" + tv_01.getWidth());
        addLeftSlidingView();


        bn_value_animator = (Button) findViewById(R.id.bn_value_animator) ;
        bn_imitation_win10 = (Button) findViewById(R.id.bn_imitation_win10) ;
        bn_imitation_wechat = (Button) findViewById(R.id.bn_imitation_wechat) ;
        bn_float_window = (Button) findViewById(R.id.bn_float_window) ;
        bn_liu_lang = (Button) findViewById(R.id.bn_liu_lang) ;
        bn_translate_animator = (Button) findViewById(R.id.bn_translate_animator) ;
        bn_recyclerView = (Button) findViewById(R.id.bn_recyclerView) ;
        bn_webView = (Button) findViewById(R.id.bn_webView) ;
        bn_fingerprint = (Button) findViewById(R.id.bn_fingerprint) ;
        bn_pedometer = (Button) findViewById(R.id.bn_pedometer) ;
        bn_tinker = (Button) findViewById(R.id.bn_tinker) ;
        bn_picture_selector = (Button) findViewById(R.id.bn_picture_selector) ;
        bn_android_art = (Button) findViewById(R.id.bn_android_art) ;
        bn_crv = (Button) findViewById(R.id.bn_crv) ;
        String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
        String imageUrl2 = "http://10.11.18.40/demo/38F843A7BE1AC297250F88FBF79D8DF3.png" ;
        ImageLoadTool.loadImage(this , imageUrl ,(ImageView) findViewById(R.id.iv_01) , 0 , null);
        ImageLoadTool.loadImage(this , imageUrl ,(ImageView) findViewById(R.id.iv_02) , 0 , null);
>>>>>>> d90c67ae3a6af1614e0f013acad935c4686bbea7
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
