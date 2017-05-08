package nfl.com.androidart.chapter01.activity;

import android.app.Activity;
import android.os.Bundle;

import nfl.com.androidart.R;

public class TransparentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }
}
