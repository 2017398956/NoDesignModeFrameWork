package com.a2017398956.nodesignmodeframework.activity;

import android.os.Bundle;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class PictureSelectorActivity extends BaseActivity {

    private static final int REQUEST_CAMERA_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selector);
//        ISNav.getInstance().init((ImageLoader) (context, path, imageView) -> {
//            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
//            Glide.with(context).load(path).into(imageView);
//        });
//        findViewById(R.id.iv_test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 自由配置选项
//                ISListConfig config = new ISListConfig.Builder()
//                        // 是否多选, 默认true
//                        .multiSelect(true)
//                        // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
//                        .rememberSelected(false)
//                        // “确定”按钮背景色
//                        .btnBgColor(Color.GRAY)
//                        // “确定”按钮文字颜色
//                        .btnTextColor(Color.BLUE)
//                        // 使用沉浸式状态栏
//                        .statusBarColor(Color.parseColor("#3F51B5"))
//                        // 返回图标ResId
//                        .backResId(com.yuyh.library.imgsel.R.drawable.ic_back)
//                        // 标题
//                        .title("图片")
//                        // 标题文字颜色
//                        .titleColor(Color.WHITE)
//                        // TitleBar背景色
//                        .titleBgColor(Color.parseColor("#3F51B5"))
//                        // 裁剪大小。needCrop为true的时候配置
//                        .cropSize(1, 1, 200, 200)
//                        .needCrop(true)
//                        // 第一个是否显示相机，默认true
//                        .needCamera(false)
//                        // 最大选择图片数量，默认9
//                        .maxNum(9)
//                        .build();
//                // 跳转到图片选择器
//                ISNav.getInstance().toListActivity(this, config, 1001);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
//            String path = data.getStringExtra("result"); // 图片地址
//            LogTool.d("NFL", "图片路径：" + path);
//        }
//    }
//
//    private void openCamera() {
//        ISCameraConfig config = new ISCameraConfig.Builder()
//                .needCrop(true) // 裁剪
//                .cropSize(1, 1, 200, 200)
//                .build();
//
//        ISNav.getInstance().toCameraActivity(this, config, REQUEST_CAMERA_CODE);
//    }
}
