package nfl.com.androidart.chapter01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;

/**
 * Activity 生命周期测试
 *
 * @author fuli.niu
 * @date 2017-4-1
 */
public class ActivityLifecycleTestActivity extends CommonActionBarActivity implements View.OnClickListener {

    private final String TAG = "ActivityLifecycleTestActivity";
    private Button bn_transparent_activity ;
    private EditText et_restore ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    LogTool.i("handler Button's width is " + bn_transparent_activity.getWidth()) ;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_test);
        bn_transparent_activity = findViewById(R.id.bn_transparent_activity) ;
        et_restore = findViewById(R.id.et_restore) ;
        handler.sendEmptyMessage(10) ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogTool.i("onRestart Button's width is " + bn_transparent_activity.getWidth()) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogTool.i("onStart Button's width is " + bn_transparent_activity.getWidth()) ;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogTool.i(TAG + " : onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogTool.i("onResume Button's width is " + bn_transparent_activity.getWidth()) ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogTool.i(TAG + ": onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogTool.i(TAG + ": onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogTool.i(TAG + ": onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogTool.i(TAG + ": onDestroy");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bn_transparent_activity) {
            startActivity(new Intent(this, TransparentActivity.class));
        } else if (id == R.id.bn_dialog_activity) {
            startActivity(new Intent(this, DialogActivity.class));
        } else if (id == R.id.bn_normal_activity) {
            startActivity(new Intent(this, NormalActivity.class));
        }
    }
}
