package com.a2017398956.nodesignmodeframework.activity;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;

import android.view.View;

import com.a2017398956.nodesignmodeframework.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class BottomSheetBehaviorTestActivity extends BaseActivity {

    private BottomSheetBehavior<NestedScrollView> behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior_test);
        findViewById(R.id.bn_hide).setOnClickListener(onClickListener);
        findViewById(R.id.bn_open).setOnClickListener(onClickListener);

        NestedScrollView nsv_list_view = (NestedScrollView) findViewById(R.id.nsv_list_view);
        behavior = BottomSheetBehavior.from(nsv_list_view);
        behavior.setHideable(true);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private final CustomOnClickListener<View> onClickListener = new CustomOnClickListener<>() {
        @Override
        public void onClick(View v) {
            super.onClick(v);
            if (v.getId() == R.id.bn_open) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else if (v.getId() == R.id.bn_hide) {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
            ToastTool.showShortToast(behavior.getState() + String.valueOf(behavior.isHideable()));
        }
    };
}
