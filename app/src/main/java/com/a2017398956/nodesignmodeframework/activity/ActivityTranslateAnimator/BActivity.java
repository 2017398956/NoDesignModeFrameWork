package com.a2017398956.nodesignmodeframework.activity.ActivityTranslateAnimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;

import java.util.Random;

public class BActivity extends Activity {

    private Button bn_b;
    private TextView tv_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        bn_b = (Button) findViewById(R.id.bn_b);
        bn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BActivity.this, AActivity.class));
                overridePendingTransition(com.nfl.libraryoflibrary.R.anim.in_from_right , com.nfl.libraryoflibrary.R.anim.out_to_left);
            }
        });

        tv_info = (TextView) findViewById(R.id.tv_info) ;
        tv_info.setText(new Random().nextInt(10000) + "");
    }
}
