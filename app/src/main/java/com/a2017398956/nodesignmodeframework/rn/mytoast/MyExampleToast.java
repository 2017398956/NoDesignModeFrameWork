package com.a2017398956.nodesignmodeframework.rn.mytoast;

import com.a2017398956.nodesignmodeframework.rn.common.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MyExampleToast extends BaseReactPackage {

    @Override
    public List<NativeModule> addNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new MyToast(reactContext));
        return modules;
    }
}
