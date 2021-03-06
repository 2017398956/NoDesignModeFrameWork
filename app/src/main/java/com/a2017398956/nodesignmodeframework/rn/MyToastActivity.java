package com.a2017398956.nodesignmodeframework.rn;

import android.os.Bundle;
import android.view.KeyEvent;

import com.a2017398956.nodesignmodeframework.rn.mytoast.MyExampleToast;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

public class MyToastActivity extends CommonActionBarActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_scramble_tickets);

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("my.toast.bundle")
                .setJSMainModulePath("index.android")
                // .setJSMainModuleName("index.android") //对应index.android.js
                .addPackage(new MainReactPackage())
                .addPackage(new MyExampleToast())
                // .setUseDeveloperSupport(BuildConfig.DEBUG) //开发者支持，BuildConfig.DEBUG的值默认是false，无法使用开发者菜单
                // .setUseDeveloperSupport(true) //开发者支持,开发的时候要设置为true，不然无法使用开发者菜单
                .setInitialLifecycleState(LifecycleState.BEFORE_RESUME)
                .build();
        // 这里的 ReactNativeView 对应 index.android.js 中 AppRegistry.registerComponent('ReactNativeView', () => ReactNativeView) 的 ReactNativeView
        mReactRootView.startReactApplication(mReactInstanceManager, "AwesomeProject", null);
        setContentView(mReactRootView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
        LogTool.i("onResume");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //当我们点击菜单的时候打开发者菜单，一个弹窗（此处需要悬浮窗权限才能显示）
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        LogTool.i("onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        /**
         * 如果点击了返回按钮，先通知 ReactInstanceManager ，
         * 待 {@link ReactInstanceManager#onBackPressed()} 执行完毕会调用
         * {@link DefaultHardwareBackBtnHandler#invokeDefaultOnBackPressed()}
         * */
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
        LogTool.i("onBackPressed");
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        // 这里用于回调系统的返回按钮事件
        super.onBackPressed();
        LogTool.i("invokeDefaultOnBackPressed");
    }

    @Override
    protected void onPause() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
        LogTool.i("onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
//             mReactInstanceManager.destroy();2017398956

        }
        LogTool.i("onDestroy");
        super.onDestroy();
    }


}
