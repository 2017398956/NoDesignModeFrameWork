package nfl.com.androidart.chapter01.activity;

import android.os.Bundle;

import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;

/**
 * Activity 生命周期测试
 * @author fuli.niu
 * @date 2017-4-1
 */
public class ActivityLifecycleTestActivity extends CommonActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_test);
    }
}
