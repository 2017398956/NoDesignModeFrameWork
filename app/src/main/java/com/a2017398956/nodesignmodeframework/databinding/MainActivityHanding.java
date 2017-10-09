package com.a2017398956.nodesignmodeframework.databinding;

import android.content.Intent;
import android.view.View;

import com.a2017398956.nodesignmodeframework.R;
import com.a2017398956.nodesignmodeframework.activity.ActivityTranslateAnimator.AActivity;
import com.a2017398956.nodesignmodeframework.activity.BottomSheetBehaviorTestActivity;
import com.a2017398956.nodesignmodeframework.activity.FingerprintTestActivity;
import com.a2017398956.nodesignmodeframework.activity.ImitationWin10ProgressBarActivity;
import com.a2017398956.nodesignmodeframework.activity.MainActivity;
import com.a2017398956.nodesignmodeframework.activity.PedometerActivity;
import com.a2017398956.nodesignmodeframework.activity.PictureSelectorActivity;
import com.a2017398956.nodesignmodeframework.activity.PullToRefreshSwipeMenuListViewActivity;
import com.a2017398956.nodesignmodeframework.activity.RecyclerViewActivity;
import com.a2017398956.nodesignmodeframework.activity.TestActivity;
import com.a2017398956.nodesignmodeframework.activity.TestImitatationWeChatActivity;
import com.a2017398956.nodesignmodeframework.activity.TinkerTestActivity;
import com.a2017398956.nodesignmodeframework.activity.ValueAnimatorTestActivity;
import com.a2017398956.nodesignmodeframework.activity.WebViewActivity;
import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity;
import com.a2017398956.nodesignmodeframework.pushtoloadmore.PushLoadMoreActivity;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.floatwindow.FloatWindowActivity;
import com.nfl.libraryoflibrary.view.traffic_float_window.TrafficFloatWindowActivity;

/**
 * Created by nfl on 2017/10/2.
 */

public class MainActivityHanding extends CustomOnClickListener {

    private MainActivity mainActivity;
    private ActivityMainBinding binding;

    private MainActivityHanding() {
    }

    public MainActivityHanding(MainActivity mainActivity, ActivityMainBinding binding) {
        this();
        this.mainActivity = mainActivity;
        this.binding = binding;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bn_result_exception:
                intent = new Intent(mainActivity, ResultInfoExceptionActivity.class);
                break;
            case R.id.bn_test:
                intent = new Intent(mainActivity, TestActivity.class);
                break;
            case R.id.bn_bottom_sheet_behavior:
                intent = new Intent(mainActivity, BottomSheetBehaviorTestActivity.class);
                break;
            case R.id.bn_ptrsml:
                intent = new Intent(mainActivity, PullToRefreshSwipeMenuListViewActivity.class);
                break;
            case R.id.ll_displayed:
                ToastTool.showShortToast("ll_displayed");
                break;
            case R.id.tv_01:
                ToastTool.showShortToast("tv_01");
                break;
            case R.id.tv_02:
                ToastTool.showShortToast("tv_02");
                break;
            case R.id.bn_value_animator:
                intent = new Intent(mainActivity, ValueAnimatorTestActivity.class);
                break;
            case R.id.bn_imitation_win10:
                intent = new Intent(mainActivity, ImitationWin10ProgressBarActivity.class);
                break;
            case R.id.bn_imitation_wechat:
                intent = new Intent(mainActivity, TestImitatationWeChatActivity.class);
                break;
            case R.id.bn_float_window:
                intent = new Intent(mainActivity, FloatWindowActivity.class);
                break;
            case R.id.bn_liu_lang:
                intent = new Intent(mainActivity, TrafficFloatWindowActivity.class);
                break;
            case R.id.bn_translate_animator:
                intent = new Intent(mainActivity, AActivity.class);
                break;
            case R.id.bn_recyclerView:
                intent = new Intent(mainActivity, RecyclerViewActivity.class);
                break;
            case R.id.bn_webView:
                intent = new Intent(mainActivity, WebViewActivity.class);
                break;
            case R.id.bn_fingerprint:
                intent = new Intent(mainActivity, FingerprintTestActivity.class);
                break;
            case R.id.bn_pedometer:
                intent = new Intent(mainActivity, PedometerActivity.class);
                break;
            case R.id.bn_tinker:
                intent = new Intent(mainActivity, TinkerTestActivity.class);
                break;
            case R.id.bn_picture_selector:
                intent = new Intent(mainActivity, PictureSelectorActivity.class);
                break;
            case R.id.bn_android_art:
                nfl.com.androidart.utils.ActivityLauncher.launchContentsActivity(mainActivity);
                break;
            case R.id.bn_crv:
                intent = new Intent(mainActivity, PushLoadMoreActivity.class);
                break;
        }
        if (null != intent) {
            mainActivity.startActivity(intent);
        }
    }
}
