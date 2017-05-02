package nfl.com.androidart.chapter01.activity;

import android.os.Bundle;

import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;

public class NormalActivity extends CommonActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
