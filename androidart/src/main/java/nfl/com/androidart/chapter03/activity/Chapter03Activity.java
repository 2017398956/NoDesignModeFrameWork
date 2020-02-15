package nfl.com.androidart.chapter03.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;
import nfl.com.androidart.chapter03.view.CustomLinearLayout;
import nfl.com.androidart.chapter03.view.CustomTextView;

public class Chapter03Activity extends CommonActionBarActivity {

    private CustomLinearLayout cll_test ;
    private CustomTextView ctv_test ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter03);
        setActionBarTitle("第 3 章 View 的事件体系");
        initViews() ;
    }

    private void initViews(){
        cll_test = findViewById(R.id.cll_test) ;
        ctv_test = findViewById(R.id.ctv_test) ;
        cll_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showShortToast("cll_test clicked");
            }
        });
        ctv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showShortToast("click");
            }
        });
    }
}
