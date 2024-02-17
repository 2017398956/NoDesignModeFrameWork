package nfl.com.androidart.chapter10.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ExceptionTool;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import java.util.Objects;
import java.util.Random;

import nfl.com.androidart.R;

public class Chapter10Activity extends CommonActionBarActivity {

    private Button bn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter10);
        bn_test = (Button) findViewById(R.id.bn_test);
        bn_test.setOnClickListener(new CustomOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if (!interactiveWithUIThread.isAlive()) {
                    interactiveWithUIThread.start();
                }
            }
        });
    }

    private final Thread interactiveWithUIThread = new Thread(new Runnable() {

        private Looper myLooper;

        private Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // 这里得到的是 UI 线程的 Looper 即：MainLooper ; MainLooper 不能 quit
                if (Looper.myLooper() != null && Looper.myLooper() != Looper.getMainLooper()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        Looper.myLooper().quitSafely();
                    } else {
                        Looper.myLooper().quit();
                    }
                }
                if (msg.what == 10) {
                    bn_test.setText("自定义 loop 已启动");
                }
                if (null != myLooper) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        myLooper.quitSafely();
                    } else {
                        myLooper.quit();
                    }
                }
            }
        };

        @Override
        public void run() {
            try {
                LogTool.i("Before Looper.loop()");
                looperPrepare();
                final Handler handler02 = new Handler(myLooper) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        LogTool.i("handler02 Looper.myLooper(): " + Looper.myLooper());
                        handler.sendEmptyMessage(10);
                    }
                };
                handler02.sendEmptyMessage(10);
                Looper.loop();
                LogTool.i("After Looper.loop()");
            } catch (Exception e) {
                LogTool.i(ExceptionTool.getExceptionTraceString(e));
            }
        }

        /**
         * 有时 myLooper 为 null 但 ，已经执行过 Looper.prepare() ;
         * TODO 找出造成这种现象的原因
         */
        private void looperPrepare() {
            try {
                if (null == myLooper) {
                    Looper.prepare();
                    myLooper = Looper.myLooper();
                }
            } catch (Exception e) {
                LogTool.i("Looper 初始化异常:" + ExceptionTool.getExceptionTraceString(e));
            } finally {
                myLooper = Looper.myLooper();
            }
        }
    });
}
