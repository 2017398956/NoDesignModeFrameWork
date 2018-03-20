package com.a2017398956.nodesignmodeframework.rn;

import com.facebook.react.ReactActivity;

/**
 * Created by fuli.niu on 2018/3/20.
 */

public class MyReactNativeActivity extends ReactActivity {
    /**
     * 这里的ReactNativeView对应index.android.js中AppRegistry.registerComponent('ReactNativeView', () => Root)的ReactNativeView
     */
    @Override
    protected String getMainComponentName() {
        return "ReactNativeView";
    }
}
