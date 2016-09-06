package com.a2017398956.nodesignmodeframework;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.nfl.libraryoflibrary.constant.ApplicationContext;
import com.nfl.libraryoflibrary.utils.CustomActivityLifecycleCallbacks;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;

/**
 * Created by fuli.niu on 2016/8/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext.applicationContext = this ;
        registerActivityLifecycleCallbacks(new CustomActivityLifecycleCallbacks());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

}
