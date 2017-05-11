package nfl.com.androidart.contents.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.nfl.libraryoflibrary.utils.ToastTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;
import com.nfl.libraryoflibrary.view.recyclerview.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import nfl.com.androidart.R;
import nfl.com.androidart.chapter02.service.BookManagerService;
import nfl.com.androidart.contents.adapter.ContentsAdapter;
import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.contents.handing.ContentsViewController;
import nfl.com.androidart.databinding.ActivityContentsBinding;

/**
 * 《Android 开发艺术探索》
 */
public class ContentsActivity extends CommonActionBarActivity {

    private ActivityContentsBinding binding;
    private View bindingView;
    private Contents contents;
    private ContentsViewController contentsViewController;
    private ContentsAdapter contentsAdapter;

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
    }

    private void initDatabinding() {
        // 测试 bn_test 的 Databinding 效果
        binding = DataBindingUtil.bind(bindingView);
        contents = new Contents();
        contents.setChapter("测试按钮");
        binding.setContents(contents);

        contentsViewController = new ContentsViewController();
        binding.setController(contentsViewController);

        // 添加目录
        String[] titles = {"第 1 章 Activity 的生命周期和启动模式", "第 2 章 IPC 机制",
                "第 3 章", "第 4 章",
                "第 5 章", "第 6 章", "第 7 章",
                "第 8 章", "第 9 章", "第 10 章 Android 的消息机制",
                "第 11 章", "第 12 章", "第 13 章",
                "第 14 章", "第 15 章"};
        List<Contents> data = new ArrayList<>();
        for (String title : titles) {
            contents = new Contents();
            contents.setChapter(title);
            data.add(contents);
        }
        contentsAdapter = new ContentsAdapter(this, data);
        binding.rvContents.setAdapter(contentsAdapter);
        binding.rvContents.addOnItemClickListener(onItemClickListener);

        // 测试 BaseAdapter 使用 Databinding
        // Contents2Adapter contents2Adapter = new Contents2Adapter(this , data) ;
        // binding.lvContents.setAdapter(contents2Adapter);
    }

    private void setListeners() {
    }

    private CustomRecyclerView.OnItemClickListener onItemClickListener = new CustomRecyclerView.OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            super.onClick(view, position);
            ToastTool.showShortToast(position + "");
            nfl.com.androidart.utils.ActivityLauncher.launchChapter(ContentsActivity.this, position);
        }
    };
}
