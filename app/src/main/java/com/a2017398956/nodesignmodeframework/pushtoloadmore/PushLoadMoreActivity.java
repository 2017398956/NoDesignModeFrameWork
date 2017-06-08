package com.a2017398956.nodesignmodeframework.pushtoloadmore;

import android.os.Bundle;
import android.os.CpuUsageInfo;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;
import com.nfl.libraryoflibrary.view.recyclerview.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PushLoadMoreActivity extends CommonActionBarActivity {

    private CustomRecyclerView crv ;
    private List<String> data ;
    private PushLoadMoreAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_load_more);
        data = new ArrayList<>() ;
        for(int i = 0 ; i < 20 ; i++){
            data.add(i + "") ;
        }
        crv = (CustomRecyclerView) findViewById(R.id.crv) ;
        adapter = new PushLoadMoreAdapter(this , data) ;
        crv.setAdapter(adapter);
    }
}
