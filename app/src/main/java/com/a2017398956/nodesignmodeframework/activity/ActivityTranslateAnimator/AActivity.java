package com.a2017398956.nodesignmodeframework.activity.ActivityTranslateAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

import java.util.Random;

public class AActivity extends BaseActivity {

    private Button bn_a, bn_b, bn_c;
    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        bn_a = findViewById(R.id.bn_a);
        bn_b = findViewById(R.id.bn_b);
        bn_c = findViewById(R.id.bn_c);
        bn_a.setOnClickListener(customOnClickListener);
        bn_b.setOnClickListener(customOnClickListener);
        bn_c.setOnClickListener(customOnClickListener);
        tv_info = findViewById(R.id.tv_info);
        tv_info.setText(new Random().nextInt(10000) + "");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogTool.i("AActivity's onNewIntent");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastTool.showShortToast("onActivityResult");
    }

    private CustomOnClickListener customOnClickListener = new CustomOnClickListener() {
        @Override
        public void onClick(View v) {
            super.onClick(v);
            Intent intent = null;
            switch (v.getId()) {
                case R.id.bn_a:
                    intent = new Intent(context, AActivity.class);
                    break;
                case R.id.bn_b:
                    intent = new Intent(context, BActivity.class);
                    break;
                case R.id.bn_c:
                    intent = new Intent(context, CActivity.class);
                    break;
            }
            if (null != intent) {
                startActivityForResult(intent , 100);
//                finish();
                overridePendingTransition(com.nfl.libraryoflibrary.R.anim.in_from_right, com.nfl.libraryoflibrary.R.anim.out_to_left);
            }
        }
    };
}
