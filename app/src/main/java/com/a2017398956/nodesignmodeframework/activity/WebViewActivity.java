package com.a2017398956.nodesignmodeframework.activity;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.ToastTool;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class WebViewActivity extends AppCompatActivity {

    private WebView wv_testJs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wv_testJs = (WebView) findViewById(R.id.wv_testJs) ;
        wv_testJs.loadUrl("http://haoma.baidu.com/query");
        WebSettings webSettings = wv_testJs.getSettings() ;
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        wv_testJs.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String js ="var form = document.getElementById(\"queryForm\");" +
                        "var code = document.getElementsByClassName(\"captcha\")[0];" +
//                        "code.src=\"http://haoma.baidu.com/captcha/image/fcf759174ebdb9b16dea3f5f0236e776dfc625d3/\";" +
//                        " console.log(code.src);" +
                        "var phoneNumber = document.getElementById(\"id_phone\");" +
                        "phoneNumber.value=\"10010\";" +
                        "var codeResult = document.getElementById(\"id_captcha_1\");" +
                        "codeResult.value = 5;" ;
                AssetManager am = getAssets() ;
                byte[] buffer = new byte[512] ;
                int readLength ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    InputStream is = am.open("injectjs.js") ;
                    while ((readLength = is.read(buffer)) != -1){
                        bos.write(buffer , 0 , readLength);
                    }
                    String jsTemp = bos.toString() ;
                    ToastTool.showShortToast(jsTemp) ;
                    wv_testJs.loadUrl("javascript:" + jsTemp);
                    LogTool.i(js) ;
                    LogTool.i(jsTemp) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                wv_testJs.loadUrl("javascript:" + js);
            }
        });
    }

    private String getJsFromUrl2String() throws Exception{
        URL url = new URL("http://www.rayray.ray/ray.js");
        InputStream in = url.openStream();
        byte buff[] = new byte[1024];
        ByteArrayOutputStream fromFile = new ByteArrayOutputStream();
        do {
            int numread = in.read(buff);
            if (numread <= 0) {
                break;
            }
            fromFile.write(buff, 0, numread);
        } while (true);
        return fromFile.toString();
    }
}
