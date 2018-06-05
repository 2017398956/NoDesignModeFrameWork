package com.a2017398956.nodesignmodeframework.rn.common;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.net.CustomHttpHelper;

import org.json.JSONObject;

public class NativeNetUtil extends ReactContextBaseJavaModule {


    public NativeNetUtil(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "NativeNetUtil";
    }

    @ReactMethod
    public void getParametersJson(Callback callback) {
        callback.invoke(new JSONObject(CustomHttpHelper.getParameters()).toString());
    }
}
