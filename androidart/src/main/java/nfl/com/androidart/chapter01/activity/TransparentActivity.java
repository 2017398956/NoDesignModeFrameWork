package nfl.com.androidart.chapter01.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nfl.com.androidart.R;

public class TransparentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }
}
