package com.a2017398956.nodesignmodeframework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.nfl.libraryoflibrary.utils.LogTool;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogTool.i("本地远程服务被启动");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogTool.i("本地远程服务被销毁");
    }

}
