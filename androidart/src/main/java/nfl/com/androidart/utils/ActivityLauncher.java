package nfl.com.androidart.utils;

import android.app.Activity;
import android.content.Intent;

import nfl.com.androidart.contents.activity.ContentsActivity;


/**
 * Created by fuli.niu on 2017/4/1.
 * Activity 跳转统一管理器
 */

public class ActivityLauncher {

    /**
     * 《Android 开发艺术探索》
     * @param activity
     */
    public static void launchContentsActivity(Activity activity){
        Intent intent = new Intent(activity , ContentsActivity.class) ;
        activity.startActivity(intent);
    }
}
