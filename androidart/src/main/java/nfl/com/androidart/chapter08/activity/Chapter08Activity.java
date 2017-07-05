package nfl.com.androidart.chapter08.activity;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;

public class Chapter08Activity extends CommonActionBarActivity {

    private Button buttonTemp;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowManagerLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter08);
        setActionBarTitle("Chapter 08");
        addWindow();
    }

    private void addWindow() {
        buttonTemp = new Button(this);
        buttonTemp.setText("Button");
        windowManagerLayoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_BASE_APPLICATION , 0, PixelFormat.TRANSPARENT);
        windowManagerLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        windowManagerLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        windowManagerLayoutParams.x = 100;
        windowManagerLayoutParams.y = 300;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(buttonTemp, windowManagerLayoutParams);
    }
}
