package nfl.com.androidart.contents.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import nfl.com.androidart.R;
import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.databinding.ActivityContentsBinding;

/**
 * 《Android 开发艺术探索》
 */
public class ContentsActivity extends CommonActionBarActivity {

    private ActivityContentsBinding binding ;
    private View bindingView ;
    private Contents contents ;

    private Button bn_test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        initView() ;
        initDatabinding() ;
        setListeners() ;
    }

    private void initView(){
        setActionBarTitle("《Android 开发艺术探索》");
        bindingView = findViewById(R.id.ll_contents) ;
        bn_test = (Button) findViewById(R.id.bn_test) ;
    }

    private void initDatabinding(){
        binding = DataBindingUtil.bind(bindingView) ;
        contents = new Contents() ;
        contents.setChapter("第一章");
        binding.setContents(contents);
    }

    private void setListeners(){
        bn_test.setOnClickListener(customOnClickListener);
    }

    private CustomOnClickListener customOnClickListener = new CustomOnClickListener(){
        @Override
        public void onClick(View v) {
            super.onClick(v);
            int i = v.getId();
            if (i == R.id.bn_test) {
                contents.setChapter("新的标题");
            }
        }
    } ;
}
