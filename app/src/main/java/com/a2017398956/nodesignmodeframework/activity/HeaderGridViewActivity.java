package com.a2017398956.nodesignmodeframework.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.view.HeaderGridView;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nfl
 */
public class HeaderGridViewActivity extends CommonActionBarActivity {

    private HeaderGridView hgv_google ;
    private SimpleAdapter adapter ;
    private List<Map<String , String>> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_grid_view);
        hgv_google = findViewById(R.id.hgv_google) ;
        ImageView imageView = new ImageView(this) ;
        imageView.setImageResource(com.nfl.libraryoflibrary.R.drawable.a);
        hgv_google.addHeaderView(imageView);
        HashMap<String , String> hashMap ;
        for(int i = 0 ; i < 100 ; i++){
            hashMap = new HashMap<>() ;
            hashMap.put("id" , i + "") ;
            data.add(hashMap) ;
        }
        adapter = new SimpleAdapter(this , data , android.R.layout.simple_list_item_1 ,
                new String[]{"id"} , new int[]{android.R.id.text1}) ;
        hgv_google.setAdapter(adapter);
    }
}
