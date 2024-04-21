package com.a2017398956.nodesignmodeframework;

import android.content.Context;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import me.weishu.reflection.Reflection;


/**
 * Created by fuli.niu on 2016/12/23.
 */

public class MyTinkerApplication extends TinkerApplication {


    public MyTinkerApplication() {
        super(
                //tinkerFlags, tinker支持的类型，dex,library，还是全部都支持！
                ShareConstants.TINKER_ENABLE_ALL,
                //ApplicationLike的实现类，只能传递字符串
                "com.a2017398956.nodesignmodeframework.MyApplication",
                //Tinker的加载器，一般来说用默认的即可
                "com.tencent.tinker.loader.TinkerLoader",
                //tinkerLoadVerifyFlag, 运行加载时是否校验dex与,ib与res的Md5
                false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onBaseContextAttached(Context base, long applicationStartElapsedTime, long applicationStartMillisTime) {
        super.onBaseContextAttached(base, applicationStartElapsedTime, applicationStartMillisTime);
        Reflection.unseal(base);
    }
}
