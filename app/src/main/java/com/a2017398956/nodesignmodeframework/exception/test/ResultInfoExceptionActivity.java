package com.a2017398956.nodesignmodeframework.exception.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.ToastTool;

/**
 * Created by fuli.niu
 */
public class ResultInfoExceptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_info_exception);
    }

    public void exceptionTest(View view){
        Intent intent = new Intent(this , CreateResultInfoActivity.class) ;
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        startActivityForResult(intent , 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastTool.showShortToast("requestCode:" + requestCode + " | "
                + "resultCode:" + resultCode + " | "
        );
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK){
            if(resultCode == 10){
                ToastTool.showShortToast("没有发生异常");
            }
        }
    }
}
