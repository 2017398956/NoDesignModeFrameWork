package com.a2017398956.nodesignmodeframework.rn.mytoast;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.nfl.libraryoflibrary.utils.net.CommonBean;
import com.nfl.libraryoflibrary.utils.net.CustomCallBack;
import com.nfl.libraryoflibrary.utils.net.CustomHttpHelper;
import com.nfl.libraryoflibrary.view.CustomProgressBarDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyToast extends ReactContextBaseJavaModule {
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public MyToast(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     * 一个可选的方法getContants返回了需要导出给JavaScript使用的常量。
     * 它并不一定需要实现，但在定义一些可以被JavaScript同步访问到的预定义的值时非常有用。
     */
    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap();
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);

        return constants;
    }

    /**
     * 要导出一个方法给JavaScript使用，Java方法需要使用注解@ReactMethod。
     * 方法的返回类型必须为void。React Native的跨语言访问是异步进行的，
     * 所以想要给JavaScript返回一个值的唯一办法是使用回调函数或者发送事件
     *
     * @param message
     * @param duration
     */
    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void getDataFromServer(String url, String jsParameters, final Callback callback) {
//        CustomProgressBarDialog.showProgressBarDialog(getCurrentActivity());
        Map<String, String> parameters = null;
        try {
            parameters = new HashMap<>();
            JSONObject jsonObject = new JSONObject(jsParameters);
            String key;
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                key = keys.next();
                parameters.put(key, jsonObject.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomCallBack<CommonBean> customCallBack = new CustomCallBack<CommonBean>(CommonBean.class) {
            @Override
            public void failure() {

            }

            @Override
            public void success(String result) {
                if(TextUtils.isEmpty(result)){
                    result = "数据异常" ;
                }
                callback.invoke(result);
            }
        };
        CustomHttpHelper.getDataFromServer(url, parameters, customCallBack);
    }

    /**
     * 模块名前的RCT前缀会被自动移除。
     * 所以如果返回的字符串为"RCTmytoast"，在JavaScript端依然可以通过React.NativeModules.mytoast访问到这个模块。
     *
     * @return
     */
    @Override
    public String getName() {
        return "MyToast";
    }
}
