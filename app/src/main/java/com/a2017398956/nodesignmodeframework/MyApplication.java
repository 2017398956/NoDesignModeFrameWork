package com.a2017398956.nodesignmodeframework;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.a2017398956.nodesignmodeframework.utils.tinker.Log.MyLogImp;
import com.a2017398956.nodesignmodeframework.utils.tinker.util.TinkerManager;
import com.nfl.libraryoflibrary.constant.ApplicationContext;
import com.nfl.libraryoflibrary.utils.CustomActivityLifecycleCallbacks;
import com.nfl.libraryoflibrary.utils.CustomBroadcastSender;
import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.utils.pedometer.SensorListener;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;
import org.acra.collector.CrashReportData;
import org.acra.config.ACRAConfiguration;
import org.acra.config.ACRAConfigurationException;
import org.acra.config.ConfigurationBuilder;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

/**
 * Created by fuli.niu on 2016/8/15.
 */

@ReportsCrashes(
//        formKey = "dGVacG0ydVHnaNHjRjVTUTEtb3FPWGc6MQ", // This is required for backward compatibility but not used
//        1.提交到服务器
//        formUri = "http://www.backendofyourchoice.com/reportpath"
//        2.吐司提醒
//        mode = ReportingInteractionMode.TOAST,
//        forceCloseDialogAfterToast = false, // optional, default false
//        resToastText = R.string.crash_toast_text
//        3.对话框提醒
//        mode = ReportingInteractionMode.DIALOG,
//        resToastText = R.string.crash_toast_text, // 可选,在数据收集完毕之前弹出一个土司
//        resDialogText = R.string.crash_dialog_text,//对话框内容
//        resDialogIcon = android.R.drawable.ic_dialog_info, //可选, 对话框的图片
//        resDialogTitle = R.string.crash_dialog_title, // 可选,对话框的标题
//        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, //可选,提示用户输入异常产生的原因等
//        resDialogEmailPrompt = R.string.crash_user_email_label, // optional. When defined, adds a user email text entry with this text resource as label. The email address will be populated from SharedPreferences and will be provided as an ACRA field if configured.
//        resDialogOkToast = R.string.crash_dialog_ok_toast // 可选,提交后弹出土司
//        4.状态栏提醒
//        resNotifTickerText = R.string.crash_notif_ticker_text,
//        resNotifTitle = R.string.crash_notif_title,
//        resNotifText = R.string.crash_notif_text,
//        resNotifIcon = android.R.drawable.stat_notify_error // optional. default is a warning sign
//        5.提交终端的配置
//        mode = ReportingInteractionMode.SILENT,
//        5-1.提交到自己的服务器
//        fromUri也可以采用https方式
//        默认采用POST方式提交
//        从ACRA 4.4.0开始,如果发送报告的方式是通过自定义的SSL,需要设置ReportsCrashes.disableSSLCertValidation = true
//        formUri = "http://yourserver.com/yourscript",
//        formUriBasicAuthLogin = "yourlogin", // 可选
//        formUriBasicAuthPassword = "y0uRpa$$w0rd", // 可选
//        5-2.提交到邮箱
        mailTo = "2017398956@qq.com",
//        因为数据长度的原因,提交到邮箱可能需要配置customReportContent参数:
        customReportContent = {ReportField.APP_VERSION_CODE,
                ReportField.USER_COMMENT,
                ReportField.BRAND,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PHONE_MODEL,
                ReportField.CUSTOM_DATA,
                ReportField.STACK_TRACE,
                ReportField.LOGCAT}
//        6.提交方式的配置
//        httpMethod = org.acra.sender.HttpSender.Method.POST
//        或者
//        httpMethod = org.acra.sender.HttpSender.Method.PUT (从4.5.0版本开始)
//        如果采用PUT方式提交,那么在提交的时候会在fromUri后拼接一段由ACRA生成的唯一的标识符,比如:
//        http://yourserver.com/yourscript/fe24835f-8117-4639-bfea-f57c77205771. fe24835f-8117-4639-bfea-f57c77205771
//        7.提交的数据的形式
//        默认采用from表单的方式提交
//        从4.5.0版本开始,可以以Json字符串方式提交: reportType = org.acra.sender.HttpSender.Type.JSON
//        8.配置超时时间
//        通过socketTimeout属性可以配置超时时间
//        9.配置自己的发送器
//        从4.0开始,就可以配置自己的发送器了
//        ACRA已经实现了3种发送器
//        GoogleFormSender: 通过HTTP POST方式发送报告到 Google Form through HTTP POST
//        HttpSender: 如果配置了formUri就会使用这个发送器,可以选择POST或PUT方式,数据可以采用FROM或者Json
//        EmailIntentSender: 选择一下字段,把他们放到Intent里,通过另一个应用来发送
)

/**
 * 9.配置自己的发送器
 * 从4.0开始,就可以配置自己的发送器了
 */
class YourOwnSender implements ReportSender {
    @Override
    public void send(@NonNull Context context, @NonNull CrashReportData errorContent) throws ReportSenderException {
        // Iterate over the CrashReportData instance and do whatever
        // you need with each pair of ReportField key / String value
    }
}

public class MyApplication extends DefaultApplicationLike {
    private Context context;
    private Application application;
    private static int sharedNumber = 1;

    public MyApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
        this.application = application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogTool.i("application is Creating");
        LogTool.i("sharedNumber:" + sharedNumber);
        sharedNumber++;
        /**
         * 这里获取 context 会为 null ，详见：https://github.com/Tencent/tinker/issues/55
         * {@link #onBaseContextAttached(Context)} 会先于{@link #onCreate()} 执行且其 context 不为 null
         * 所以，context 的初始化应该在{@link #onBaseContextAttached(Context)}处理。
         */
        // this.context = application.getApplicationContext();
//        初始化ACRA
//        ACRA.init(this);
//        YourOwnSender yourSender = new YourOwnSender(whatever, parameters, needed);
//        ACRA.getErrorReporter().setReportSender(yourSender);
//        配置自己需要的variables或者traces
//        ACRA.getErrorReporter().putCustomData("myKey", "myValue");
//        可以使用getCustomData(“myVariable”) 或者 removeCustomData(“myVariable”) 来获得或者移除某个信息
//        ACRA提供了一下几种可以管理发送器的方法
//        setReportSender(ReportSender): 设置一个新的发送器作为唯一活动的发送器
//        addReportSender(ReportSender): 向发送器列表中添加一个发送器
//        removeReportSender(ReportSender):移除一个发送器
//        removeReportSenders(Class): 移除实现某个接口的所有发送器
//        removeAllReportSenders(): 移除所有发送器

        ApplicationContext.applicationContext = context;
        if (LeakCanary.isInAnalyzerProcess(context)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        // LeakCanary.install((Application) context);
        // Normal app init code...
        application.registerActivityLifecycleCallbacks(new CustomActivityLifecycleCallbacks());
        CustomBroadcastSender.sendAppStartBroadCast(context);
        // startService(new Intent(this , DBInsightService.class)) ;
        application.startService(new Intent(context, SensorListener.class));
    }

    //    如果想让异常报告中的日志按时间顺序显示, 那么Activity需要做如下配置
//    public static void trackBreadcrumb(String event) {
//        ACRA.getErrorReporter().putCustomData("Event at " + System.currentTimeMillis(), event);
//    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate();
//        trackBreadcrumb("MyActivity.onCreate()");
//        ...
//    }
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        this.context = base;
        LogTool.i("base:" + base + ",context:" + context + ",application:" + application);
        MultiDex.install(base);
        // Create an ConfigurationBuilder. It is prepopulated with values specified via annotation.
        // Set any additional value of the builder and then use it to construct an ACRAConfiguration.
        ACRAConfiguration config = null;
        try {
            config = new ConfigurationBuilder(application).build();
            // Initialise ACRA
            ACRA.init(application, config);
        } catch (ACRAConfigurationException e) {
            LogTool.i(e.toString());
        }
        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        // optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());

        // installTinker after load multiDex
        // or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        application.registerActivityLifecycleCallbacks(callback);
    }
}
