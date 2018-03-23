package com.a2017398956.nodesignmodeframework;

import android.widget.Toast;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.util.Arrays;
import java.util.List;

import cn.reactnative.modules.update.UpdateContext;
import cn.reactnative.modules.update.UpdatePackage;

/**
 * Created by fuli.niu on 2016/12/23.
 */

public class MyTinkerApplication extends TinkerApplication implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return !BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.asList(
                    new MainReactPackage()
                    ,new UpdatePackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index.android";
        }

        @Override
        protected String getJSBundleFile() {
            LogTool.i("bundle:" + UpdateContext.getBundleUrl(MyTinkerApplication.this));
            return UpdateContext.getBundleUrl(MyTinkerApplication.this);
//            return null;
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

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
        SoLoader.init(this, /* native exopackage */ false);
    }

}
