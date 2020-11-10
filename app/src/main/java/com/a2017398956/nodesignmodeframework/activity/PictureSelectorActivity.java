package com.a2017398956.nodesignmodeframework.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.a2017398956.nodesignmodeframework.R;
import com.bumptech.glide.Glide;
import com.nfl.libraryoflibrary.view.BaseActivity;
//import com.yuyh.library.imgsel.ImageLoader;
//import com.yuyh.library.imgsel.ImgSelActivity;
//import com.yuyh.library.imgsel.ImgSelConfig;

public class PictureSelectorActivity extends BaseActivity {

    private ImageView iv_test;
    // 自定义图片加载器
//    private ImageLoader loader = new ImageLoader() {
//        @Override
//        public void displayImage(Context context, String path, ImageView imageView) {
//            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
//            Glide.with(context).load(path).into(imageView);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selector);
        iv_test = (ImageView) findViewById(R.id.iv_test);
        iv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImgSelConfig config = new ImgSelConfig.Builder(PictureSelectorActivity.this, loader)
//                        // 是否多选
//                        .multiSelect(true)
//                        // “确定”按钮背景色
//                        .btnBgColor(Color.GRAY)
//                        // “确定”按钮文字颜色
//                        .btnTextColor(Color.BLUE)
//                        // 使用沉浸式状态栏
//                        .statusBarColor(Color.parseColor("#F00000"))
//                        // 返回图标ResId
//                        .backResId(com.nfl.libraryoflibrary.R.drawable.ic_back)
//                        // 标题
//                        .title("图片")
//                        // 标题文字颜色
//                        .titleColor(Color.WHITE)
//                        // TitleBar背景色
//                        .titleBgColor(Color.parseColor("#3F51B5"))
//                        // 裁剪大小。needCrop为true的时候配置
//                        .cropSize(1, 1, 200, 200)
//                        .needCrop(false)
//                        // 第一个是否显示相机
//                        .needCamera(false)
//                        // 最大选择图片数量
//                        .maxNum(9)
//                        .build();
//                // 跳转到图片选择器
//                ImgSelActivity.startActivity(PictureSelectorActivity.this, config, 10);
            }
        });
    }
}
