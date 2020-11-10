package com.a2017398956.nodesignmodeframework.activity;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;

import com.a2017398956.nodesignmodeframework.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class BottomSheetBehaviorTestActivity extends BaseActivity {

    private Button bn_open , bn_hide ;
    private NestedScrollView nsv_list_view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior_test);

        bn_hide = (Button) findViewById(R.id.bn_hide) ;
        bn_open = (Button) findViewById(R.id.bn_open) ;
        nsv_list_view = (NestedScrollView) findViewById(R.id.nsv_list_view) ;

        bn_hide.setOnClickListener(onClickListener);
        bn_open.setOnClickListener(onClickListener);

    }

    private CustomOnClickListener onClickListener = new CustomOnClickListener(){
        @Override
        public void onClick(View v) {
            super.onClick(v);
            BottomSheetBehavior behavior = BottomSheetBehavior.from(nsv_list_view) ;
            behavior.setHideable(true);
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            switch (v.getId()){
                case R.id.bn_open :
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    break;
                case R.id.bn_hide :
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    break;
            }
            ToastTool.showShortToast(behavior.getState() + "" + behavior.isHideable());
        }
    } ;
}
