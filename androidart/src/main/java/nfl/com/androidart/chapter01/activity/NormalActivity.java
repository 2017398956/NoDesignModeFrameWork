package nfl.com.androidart.chapter01.activity;

import android.os.Bundle;

import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;

public class NormalActivity extends CommonActionBarActivity {

    private final String TAG = NormalActivity.class.getSimpleName() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogTool.i(TAG + " : onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogTool.i(TAG + " : onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogTool.i(TAG + " : onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogTool.i(TAG + " : onDestroy");
        LogTool.i(TAG + " : onDestroy success !");
    }
}
