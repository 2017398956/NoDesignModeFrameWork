package com.a2017398956.nodesignmodeframework.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.ToastTool;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class FingerprintTestActivity extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private FingerprintManager.CryptoObject cryptoObject;
    private Cipher cipher;
    private CancellationSignal cancellationSignal;
    private FingerprintManager.AuthenticationCallback authenticationCallback;
    private Handler handler;
    private TextView tv_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_test);
        tv_info = (TextView) findViewById(R.id.tv_info) ;
        tv_info.setText(Build.VERSION_CODES.M + "");
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        authenticationCallback = new FingerprintManager.AuthenticationCallback() {

            /**
             * 当多次（经测试为第6次）无法正确识别指纹时触法（此时不会调用{@link #onAuthenticationFailed()} ），
             * 并且系统会暂时停止指纹识别功能的使用；
             * 即：重新开启指纹识别功能时，即使正确指纹也会直接跳转到这里
             * @param errorCode
             * @param errString
             */
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                LogTool.i("onAuthenticationError");
            }

            /**
             * 当指纹识别模块无法识别指纹时会触发，如：手指滑动过快，手指或采集指纹区不干净
             * @param helpCode
             * @param helpString
             */
            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
                LogTool.i("onAuthenticationHelp");
            }

            /**
             * 指纹识别成功，并结束本次指纹识别，再次识别时需要重新调用
             * {@link android.hardware.fingerprint.FingerprintManager#authenticate(FingerprintManager.CryptoObject, CancellationSignal, int, FingerprintManager.AuthenticationCallback, Handler)}
             * @param result
             */
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                LogTool.i("onAuthenticationSucceeded");
            }

            /**
             * 指纹识别失败
             */
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                LogTool.i("onAuthenticationFailed");
            }
        };

        try {
            cipher = Cipher.getInstance("DES") ;
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            LogTool.i("加密成功") ;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        if (null != fingerprintManager) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (fingerprintManager.isHardwareDetected()) {
                // 如果探测到指纹识别硬件
                if (fingerprintManager.hasEnrolledFingerprints()) {
                    // 是否录入过指纹
                    ToastTool.showShortToast("录入过指纹");
                }
                // 进行指纹验证
//                fingerprintManager.authenticate(cryptoObject , cancellationSignal , 0 , authenticationCallback, handler);
                fingerprintManager.authenticate(null , null, 0, authenticationCallback, null);
            } else {
                ToastTool.showShortToast("您的设备不支持指纹识别");
            }

        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(null != locationManager){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ToastTool.showShortToast("经纬度：" + location.getLatitude() + "," + location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    ToastTool.showShortToast("onStatusChanged") ;
                }

                @Override
                public void onProviderEnabled(String provider) {
                    ToastTool.showShortToast("onProviderEnabled") ;
                }

                @Override
                public void onProviderDisabled(String provider) {
                    ToastTool.showShortToast("onProviderDisabled") ;
                }
            });
        }
    }
}
