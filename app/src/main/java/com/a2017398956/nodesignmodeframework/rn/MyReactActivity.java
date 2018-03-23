package com.a2017398956.nodesignmodeframework.rn;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.a2017398956.nodesignmodeframework.R;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.nfl.libraryoflibrary.utils.LogTool;

import java.util.Arrays;

import cn.reactnative.modules.update.UpdateContext;
import cn.reactnative.modules.update.UpdatePackage;

/**
 * @author nfl
 */
public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private LinearLayout mReactLayout;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_react);
        mReactLayout = findViewById(R.id.layout);

        mReactRootView = new ReactRootView(this);
        mReactLayout.addView(mReactRootView);

        ReactInstanceManagerBuilder mReactInstanceManagerBuilder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                // 对应index.android.js
                .setJSMainModulePath("index.android")
                .addPackages(Arrays.asList(new MainReactPackage(), new UpdatePackage()))
                // 开发者支持，BuildConfig.DEBUG的值默认是false，无法使用开发者菜单
                // .setUseDeveloperSupport(BuildConfig.DEBUG)
                // 开发者支持,开发的时候要设置为true，不然无法使用开发者菜单
                .setUseDeveloperSupport(false)
                .setInitialLifecycleState(LifecycleState.RESUMED);
        // 使用热修复时必须加上 jsBundleFile
        if (!TextUtils.isEmpty(UpdateContext.getBundleUrl(this))) {
            mReactInstanceManagerBuilder.setJSBundleFile(UpdateContext.getBundleUrl(this));
        }
        mReactInstanceManager = mReactInstanceManagerBuilder.build();
        // 这里的 ReactNativeView 对应 index.js 中
        // AppRegistry.registerComponent('ReactNativeView', () => Root) 的 ReactNativeView
        mReactRootView.startReactApplication(mReactInstanceManager, "nodesignmodeframework", null);
        LogTool.i("rn load success");
        // setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //当我们点击菜单的时候打开发者菜单，一个弹窗（此处需要悬浮窗权限才能显示）
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
