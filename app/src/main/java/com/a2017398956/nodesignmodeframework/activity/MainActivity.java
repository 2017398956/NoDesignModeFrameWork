package com.a2017398956.nodesignmodeframework.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a2017398956.nodesignmodeframework.R;
import com.a2017398956.nodesignmodeframework.databinding.ActivityMainBinding;
import com.a2017398956.nodesignmodeframework.databinding.MainActivityHanding;
import com.a2017398956.nodesignmodeframework.service.Remote2Service;
import com.a2017398956.nodesignmodeframework.service.RemoteService;
import com.google.android.material.snackbar.Snackbar;
import com.nfl.apt.annotation.OnClick;
import com.nfl.apt.annotation.TestAnnotation;
import com.nfl.libraryoflibrary.constant.ApplicationContext;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ExecShell;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.PhoneInfoTool;
import com.nfl.libraryoflibrary.utils.RootDetectorTool;
import com.nfl.libraryoflibrary.utils.SharePreferenceTool;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.utils.image.ImageLoadTool;
import com.nfl.libraryoflibrary.view.BaseActivity;
import com.nfl.libraryoflibrary.view.CustomHorizontalLeftSlidingView2;

import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_MOVIES;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityHanding mainActivityHanding;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    @TestAnnotation
    private ConstraintLayout constraint_layout;

    String[] permissions = {Manifest.permission.READ_CONTACTS};
    Context applicationContext = ApplicationContext.applicationContext;
    Activity activity = this;

    @OnClick(R.id.bn_test_aop)
    public void onButtonClick() {
        // ToastTool.showShortToast("onButtonClick");
        Toast.makeText(context, "onButtonClick", Toast.LENGTH_SHORT).show();
        readContacts("fuli.niu");
        if (true) {
            return;
        }

        List<String> permissionList = new ArrayList<>();// 用于保存未通过的权限
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (permissionList.size() == 0) {
            // TODO : 执行被注解的方法
        } else {
            for (int i = 0; i < permissionList.size(); i++) {
                if (ContextCompat.checkSelfPermission(applicationContext, permissionList.get(i)) != PackageManager.PERMISSION_GRANTED) {
                    // 没有得到授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionList.get(i))) {
                        // 没有权限，被拒绝过至少一次且没有点击 再不提醒
                        ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(), 0);
                        break;
                    } else {
                        // 情景一：没有权限且没有被拒绝过
                        // 情景二：没有权限且点击了 再不提醒 ，权限申请会直接返回失败
                        if (SharePreferenceTool.get(this, permissionList.get(i), null) != null) {
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissionList.get(i))) {
                                // 选择不再询问，并点击过拒绝
                                // TODO :根据 permissionList 弹出提示框，以手动授权
                                // showAlertDialog("为了正常使用某些功能,请开启联系人权限", "取消", "手动授权", new DialogInterface.OnClickListener() {
                                // @Override
                                // public void onClick(DialogInterface dialog, int which) {
                                // Uri uri = Uri.parse("package:" + getPackageName());//包名
                                // Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", uri);
                                // startActivity(intent);
                                // }
                                // });
                                break;
                            }
                        } else {
                            if (permissionList.size() - 1 == i) {
                                ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(), 0);
                            }
                            SharePreferenceTool.saveObject(false, permissionList.get(i));
                        }
                    }
                }
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
//                    constraint_layout.sendAccessibilityEvent();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(null);
        fileSystemDescription();
        LogTool.i("cmd: " + new ExecShell().executeCommand(new String[]{"ls"}));
        handler.sendEmptyMessageDelayed(2, 2000);
        hiddenBackIcon();
        setActionBarTitle("主页");
        initDataBinding();
        addAnotherLeftSlidingView();
        setListeners();
        printRootInfo();
        Snackbar snackbar = Snackbar.make(binding.constraintLayout, "Test snackbar", Snackbar.LENGTH_LONG).setAction("action",
                new CustomOnClickListener() {
                    @Override
                    public void onClick(View v) {
                        super.onClick(v);
                        ToastTool.showShortToast("Action");
                    }
                });

        snackbar.show();
        // ViewFinder.inject(this);
        startService(new Intent(this, RemoteService.class));
        startService(new Intent(this, Remote2Service.class));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopService(new Intent(MainActivity.this, RemoteService.class));
            }
        }, 3000);
        openNotification();
        // startService(new Intent(this , SkipAdsService.class)) ;
    }

    private void openNotification(){


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        String id = "channel_001";
        String name = "name" ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//判断API
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("通知")
                    .setContentText("你有一个新的通知")
                    // .setContentIntent(pi)
                    .setLights(Color.GREEN, 1000, 1000)//设置三色灯
                    .setSmallIcon(R.drawable.icon_install).build();
        }else{
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle("通知")
                    .setContentText("你有新的通知")
                    .setSmallIcon(R.drawable.icon_install)
                    .setOngoing(true)
                    // .setContentIntent(pi)
                    .setLights(Color.GREEN, 1000, 1000)//设置三色灯
                    .setChannelId(id).build();//无效
        }

//         notification = new Notification.Builder(this)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources() , R.drawable.icon_adblock))
//                .setSmallIcon(R.drawable.icon_install)
//                .setTicker("ticker")
//                .setSubText("subtext")
//                .setContentText("跳过广告")
//                .build() ;
//        notification.tickerText = "tickerText" ;
//        // 在构造方法中设置无效
//        notification.icon = R.drawable.icon_adblock;
//        notification.when =  System.currentTimeMillis();
        notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻 Flag

//        Intent intent = new Intent(this, SkipAdsService.class);

//        PendingIntent contextIntent = PendingIntent.getActivity(this, 0,
//                intent, 0);
//        notification.setLatestEventInfo(getApplicationContext(),
//                getString(R.string.app_name), "点击查看", contextIntent);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0) {
                    List<String> permissionList = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            permissionList.add(permissions[i]);
                        }
                    }
                    if (0 == permissionList.size()) {
                        // TODO : 被注解的方法
                    } else {
                        for (int i = 0; i < permissionList.size(); i++) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionList.get(i))) {
                                // 没有权限，被拒绝过至少一次且没有点击 再不提醒
                                ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(), 0);
                                break;
                            } else {
                                // TODO :根据 permissionList 弹出提示框，以手动授权
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    private void initDataBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, ll_data_binding, true);
        ll_data_binding.setVisibility(View.VISIBLE);
        mainActivityHanding = new MainActivityHanding(this, binding);
        binding.setMainActivityHanding(mainActivityHanding);

        String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
        String imageUrl2 = "http://10.11.18.40/demo/38F843A7BE1AC297250F88FBF79D8DF3.png";
        ImageLoadTool.loadImage(this, imageUrl, binding.iv01, 0, null);
        ImageLoadTool.loadImage(this, imageUrl2, binding.iv02, 0, null);
    }

    private void setListeners() {
        binding.llLeftSliding.setOnTouchListener(onTouchListener);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                LogTool.i("ACTION_UP");
            }
            LogTool.i("ACTION_UP" + motionEvent.getAction());
            return false;
        }
    };

    private void addAnotherLeftSlidingView() {
        LinearLayout ll01 = new LinearLayout(this);
        Button t01 = new Button(this);
        t01.setWidth(PhoneInfoTool.getScreenWidth(this));
        t01.setText("test");
        t01.setGravity(Gravity.CENTER);
        t01.setHeight(180);
        t01.setClickable(false);
        ll01.addView(t01);

        LinearLayout ll02 = new LinearLayout(this);
        TextView t02 = new TextView(this);
        t02.setWidth(180);
        t02.setText("yes");
        t02.setHeight(180);
        t02.setClickable(true);
        t02.setGravity(Gravity.CENTER);
        t02.setBackgroundColor(Color.RED);
        ll02.addView(t02);
        CustomHorizontalLeftSlidingView2 customHorizontalLeftSlidingView2 = new CustomHorizontalLeftSlidingView2(this, ll01, ll02);
        binding.llRootView.addView(customHorizontalLeftSlidingView2.getView());
    }

    private void printRootInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> filterInfos = new ArrayList<>();// 判断是否有 root 可能的关键词
                filterInfos.add("root");
                final String rootDetailInfo = new RootDetectorTool(ApplicationContext.applicationContext, filterInfos).getRootDetailInfo();
//                Looper.prepare();
//                binding.tvTestInfo.setText(rootDetailInfo);
//                Looper.loop();
                binding.tvTestInfo.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvTestInfo.setText(rootDetailInfo);
                    }
                });
            }
        }).start();
    }

    private String readContacts(String contact) {
        return "sfdjl";
    }

    private void fileSystemDescription() {
        // /data
        LogTool.i("Environment.getDataDirectory():" + Environment.getDataDirectory().getAbsolutePath());
        // Environment.getRootDirectory()
        LogTool.i("Environment.getRootDirectory():" + Environment.getRootDirectory().getAbsolutePath());
        // /cache
        LogTool.i("Environment.getDownloadCacheDirectory():" + Environment.getDownloadCacheDirectory().getAbsolutePath());
        /*
         * /storage/emulated/0/Movies（根据不同的版本会有差异，多张内置卡是不是编号从 0 到 n ？）这个路径是真实的 SDCard（手机内置的 SDCard）路径；
         * /storage/self/primary/Movies 也等效于 /storage/self/primary ，但 primary 是对 /storage/emulated/0 的引用，是 link 类型的，有关内容参考 Linux 文件引用；
         * /storage/131A-2419（也可能是别的名称，这个目录都是真实路径） 下的文件夹目录和 /storage/emulated/0 相同，一般是手机插的 TF 卡；
         * 另外，/sdcard 是对 /storage/emulated/0 的引用，不是真正的文件。
         */
        LogTool.i("Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES):" + Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).getAbsolutePath());

        LogTool.i("外置存储卡：" + Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).getParentFile().getParentFile().getParentFile().listFiles()[0].getAbsolutePath());
    }

    private void cancelNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }
}
