package nfl.com.androidart.chapter10.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ExceptionTool;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import java.util.Random;

import nfl.com.androidart.R;

public class Chapter10Activity extends CommonActionBarActivity {

    private Button bn_test;
    private Handler handlerActivity = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            bn_test.setText("handlerActivity");
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter10);
        bn_test = (Button) findViewById(R.id.bn_test);
        bn_test.setOnClickListener(new CustomOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if (interactiveWithUIThread.isAlive()) {
                } else {
                    interactiveWithUIThread.start();
                }

            }
        });
    }

    private Thread interactiveWithUIThread = new Thread(new Runnable() {

        private Looper myLooper;

        private android.os.Handler handler = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogTool.i("接收到 Handler 发送的信息");
                LogTool.i("handler Looper.myLooper(): " + Looper.myLooper());
                // 这里得到的是 UI 线程的 Looper 即：MainLooper ; MainLooper 不能 quit
                if (Looper.myLooper() != null && Looper.myLooper() != Looper.getMainLooper()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        Looper.myLooper().quitSafely();
                    } else {
                        Looper.myLooper().quit();
                    }
                }
                if (msg.what == 10) {
                    bn_test.setText("非 UI 线程修改 UI 成功 " + new Random(100));
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
                LogTool.i("Looper.myLooper(): " + Looper.myLooper() + " , Looper.getMainLooper(): " + Looper.getMainLooper());
//                looperPrepare();
                LogTool.i("Looper.myLooper(): " + Looper.myLooper() + " , Looper.getMainLooper(): " + Looper.getMainLooper());
//                final Handler handler02 = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        LogTool.i("handler02 Looper.myLooper(): " + Looper.myLooper()) ;
//                    }
//                } ;
                // handler02.sendEmptyMessage(10) ;
                handler.sendEmptyMessage(10);
//                Looper.loop();
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
                if(null == myLooper){
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
