package com.a2017398956.test01library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.nfl.libraryoflibrary.utils.LogTool;

public class Test01Receiver extends BroadcastReceiver {
    public Test01Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String actionTemp = intent.getAction() ;
        LogTool.i(actionTemp);
        if("cn.bill.app.gw.startapp".equals(actionTemp)){
            Intent nextActivity = new Intent() ;
            nextActivity.setClass(context , Test01MainActivity.class) ;
            nextActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
            context.startActivity(nextActivity);
        }
    }
}
