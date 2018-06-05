package com.a2017398956.nodesignmodeframework.rn.common;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseReactPackage implements ReactPackage {

    /**
     * 需要在应用的Package类的createNativeModules方法中添加这个模块。
     * 如果模块没有被注册，它也无法在JavaScript中被访问到。
     *
     * @param reactContext
     * @return
     */
    @Override
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new NativeNetUtil(reactContext));
        modules.addAll(addNativeModules(reactContext));
        return modules;
    }

    public abstract List<NativeModule> addNativeModules(ReactApplicationContext reactContext);

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    //    @Override
//    public List<Class<? extends JavaScriptModule>> createJSModules() {
//        return Collections.emptyList();
//    }
}
