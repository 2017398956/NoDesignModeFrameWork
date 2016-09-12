package com.a2017398956.nodesignmodeframework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.PullToRefreshSwipeMenuListView;
import com.nfl.libraryoflibrary.view.pulltorefresh.interfaces.IXListViewListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PullToRefreshSwipeMenuListViewActivity extends AppCompatActivity {

    private PullToRefreshSwipeMenuListView ptrsm_list_view;
    private SimpleAdapter adapter;
    private List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_swipe_menu_list_view);
        ptrsm_list_view = (PullToRefreshSwipeMenuListView) findViewById(R.id.ptrsm_list_view);
        ptrsm_list_view.setPullLoadEnable(true);
        ptrsm_list_view.setPullRefreshEnable(true);
        data = new ArrayList<Map<String, String>>() ;
        Map<String , String> map ;
        for(int i = 0 ; i < 20 ;i++){
            map = new HashMap<String , String>() ;
            map.put("title" , i + "") ;
            data.add(map);
        }
        adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_1,
                new String[]{"title"},
                new int[]{android.R.id.text1});
        ptrsm_list_view.setAdapter(adapter);
        ptrsm_list_view.setXListViewListener(ixListViewListener);
    }

    private IXListViewListener ixListViewListener = new IXListViewListener() {
        @Override
        public void onRefresh() {
            ptrsm_list_view.canLoadMore = true ;
            ptrsm_list_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastTool.showCustomShortToast("onRefresh");
                    ptrsm_list_view.stopRefresh();
                }
            } , 2000) ;
        }

        @Override
        public void onLoadMore() {
            ToastTool.showCustomShortToast("footerViewsCount" + ptrsm_list_view.getFooterViewsCount() );
            ptrsm_list_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptrsm_list_view.stopLoadMore();
                }
            } , 5000) ;
        }
    } ;
}
