package nfl.com.androidart.chapter01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_test);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogTool.i(TAG + ": onPause");
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
