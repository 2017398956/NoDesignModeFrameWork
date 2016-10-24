package com.a2017398956.nodesignmodeframework.activity.ActivityTranslateAnimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.ToastTool;

import org.w3c.dom.Text;

import java.util.Random;

public class AActivity extends Activity {

    private Button bn_a;
    private TextView tv_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        bn_a = (Button) findViewById(R.id.bn_a);
        bn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AActivity.this, BActivity.class) , 100);
//                finish();
                overridePendingTransition(com.nfl.libraryoflibrary.R.anim.in_from_right , com.nfl.libraryoflibrary.R.anim.out_to_left);
            }
        });
        tv_info = (TextView) findViewById(R.id.tv_info) ;
        tv_info.setText(new Random().nextInt(10000) + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastTool.showShortToast("onActivityResult");
    }
}
