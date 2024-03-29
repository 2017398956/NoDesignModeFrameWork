package com.a2017398956.nodesignmodeframework.activity;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.widget.TextView;

import com.a2017398956.nodesignmodeframework.R;
import com.a2017398956.nodesignmodeframework.fragment.Fragment01;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class FingerprintTestActivity extends BaseActivity {

    private final static String TAG = "FingerprintTestActivity";
    private FingerprintManager fingerprintManager;
    private FingerprintManager.CryptoObject cryptoObject;
    private Cipher cipher;
    private CancellationSignal cancellationSignal;
    private FingerprintManager.AuthenticationCallback authenticationCallback;
    private Handler handler;
    private TextView tv_info;
    private TabLayout tab_layout;
    private CollapsingToolbarLayout collapsing_tool_bar_layout;
    private Toolbar tool_bar;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_test);
        hideAppBar();
        tool_bar = (Toolbar) findViewById(R.id.tool_bar);
//        tool_bar.setTitle("Title");
        tool_bar.setNavigationIcon(com.nfl.libraryoflibrary.R.drawable.lol_icon_back_drawable);
//        tool_bar.setLogo(R.mipmap.ic_launcher);

        collapsing_tool_bar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar_layout);
        collapsing_tool_bar_layout.setTitle("Title");

        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_info.setText(Build.VERSION_CODES.M + "");
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new Fragment01();
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        tab_layout.setupWithViewPager(view_pager);
        tab_layout.getTabAt(0).setText("Tab01");
        tab_layout.getTabAt(1).setText("Tab02");
//        tab_layout.addTab(tab_layout.getTabAt(0).setText("Tab01"), true);
//        tab_layout.addTab(tab_layout.getTabAt(1).setText("Tab02"));
//        tab_layout.addTab(tab_layout.newTab().setText("Tab01"), 0 , true);
//        tab_layout.addTab(tab_layout.newTab().setText("Tab02") , 1 );
//        tab_layout.addTab(tab_layout.newTab().setText("Tab03") , 2);
        testFinger();
    }

    /**
     * 原生方法没有 GMS 框架无效
     */
    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (null != locationManager) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ToastTool.showShortToast("经纬度：" + location.getLatitude() + "," + location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    ToastTool.showShortToast("onStatusChanged");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    ToastTool.showShortToast("onProviderEnabled");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    ToastTool.showShortToast("onProviderDisabled");
                }
            });
        }
    }

    private void testFinger() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            LogTool.d(TAG, "Android 6.0 之前没有统一的指纹 api");
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
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
                 * {@link FingerprintManager#authenticate(FingerprintManager.CryptoObject, CancellationSignal, int, FingerprintManager.AuthenticationCallback, Handler)}
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
                cipher = Cipher.getInstance("DES");
                cryptoObject = new FingerprintManager.CryptoObject(cipher);
                LogTool.i("加密成功");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                e.printStackTrace();
            }

            fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            if (null != fingerprintManager) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    LogTool.d(TAG, "没有指纹权限");
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
                    fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
                } else {
                    ToastTool.showShortToast("您的设备不支持指纹识别");
                }
            }
        } else {
            CancellationSignal cancellationSignal = new CancellationSignal();
            cancellationSignal.setOnCancelListener(() -> LogTool.d(TAG, "用户取消了授权"));
            Executor executor = Executors.newSingleThreadExecutor();
            new BiometricPrompt.Builder(this).setTitle("测试指纹授权")
                    .setNegativeButton("取消", executor, (dialog, which) -> LogTool.d(TAG, "用户点击了取消了授权")).build()
                    .authenticate(cancellationSignal,
                            executor,
                            new BiometricPrompt.AuthenticationCallback() {
                                @Override
                                public void onAuthenticationError(int errorCode, CharSequence errString) {
                                    super.onAuthenticationError(errorCode, errString);
                                    LogTool.d(TAG, "授权错误，errorCode：" + errorCode + " errorString:" + errString);
                                }

                                @Override
                                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                                    super.onAuthenticationHelp(helpCode, helpString);
                                    LogTool.d(TAG, "授权帮助，helpCode：" + helpCode + " helpString:" + helpString);
                                }

                                @Override
                                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                                    super.onAuthenticationSucceeded(result);
                                    LogTool.d(TAG, "授权成功，result：" + result);
                                }

                                @Override
                                public void onAuthenticationFailed() {
                                    super.onAuthenticationFailed();
                                    LogTool.d(TAG, "授权失败");
                                }
                            });
        }
    }
}
