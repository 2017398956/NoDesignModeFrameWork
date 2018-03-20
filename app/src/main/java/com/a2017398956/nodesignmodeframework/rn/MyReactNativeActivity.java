package com.a2017398956.nodesignmodeframework.rn;

import com.facebook.react.ReactActivity;

/**
 * Created by fuli.niu on 2018/3/20.
 */

public class MyReactNativeActivity extends ReactActivity {

    /**
     * 这里的 ReactNativeView 对应 index.js 中
     * AppRegistry.registerComponent('ReactNativeView', () => Root) 的R eactNativeView
     */
    @Override
    protected String getMainComponentName() {
        return "nodesignmodeframework";
    }
}
