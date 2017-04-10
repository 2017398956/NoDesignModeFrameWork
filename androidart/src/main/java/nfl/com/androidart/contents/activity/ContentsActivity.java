package nfl.com.androidart.contents.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;
import com.nfl.libraryoflibrary.view.recyclerview.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import nfl.com.androidart.R;
import nfl.com.androidart.contents.adapter.ContentsAdapter;
import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.databinding.ActivityContentsBinding;

/**
 * 《Android 开发艺术探索》
 */
public class ContentsActivity extends CommonActionBarActivity {

    private ActivityContentsBinding binding;
    private View bindingView;
    private Contents contents;

    private Button bn_test;

    private ContentsAdapter contentsAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        initView();
        initDatabinding();
        setListeners();
    }

    private void initView() {
        setActionBarTitle("《Android 开发艺术探索》");
        bindingView = findViewById(R.id.ll_contents);
        bn_test = (Button) findViewById(R.id.bn_test);
    }

    private void initDatabinding() {
        binding = DataBindingUtil.bind(bindingView);
        contents = new Contents();
        contents.setChapter("第一章");
        binding.setContents(contents);
        contents.setChapter("第二章");
        List<Contents> data = new ArrayList<>();
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第三章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第四章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第五章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第六章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第七章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第八章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第九章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十一章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十二章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十三章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十四章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十五章");
        data.add(contents);
        contents = new Contents();
        contents.setChapter("第十六章");
        data.add(contents);

        contentsAdapter = new ContentsAdapter(this, data);
        CustomRecyclerView.OnItemClickListener onItemClickListener = new CustomRecyclerView.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                super.onClick(view, position);
                ToastTool.showShortToast(position + "");
            }
        };
        binding.rvContents.setAdapter(contentsAdapter);
        binding.rvContents.addOnItemClickListener(onItemClickListener);

        // Contents2Adapter contents2Adapter = new Contents2Adapter(this , data) ;
        // binding.lvContents.setAdapter(contents2Adapter);
    }

    private void setListeners() {
        bn_test.setOnClickListener(customOnClickListener);
    }

    private CustomOnClickListener customOnClickListener = new CustomOnClickListener() {
        @Override
        public void onClick(View v) {
            super.onClick(v);
            int i = v.getId();
            if (i == R.id.bn_test) {
                contents.setChapter("新的标题");
                if(contentsAdapter != null){
                    contentsAdapter.notifyItemChanged(contentsAdapter.getItemCount() - 1);
                }
            }
        }
    };
}
