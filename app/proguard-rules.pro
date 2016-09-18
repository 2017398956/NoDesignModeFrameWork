# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\nfl\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 解决
# Error:warning: Ignoring InnerClasses attribute for an anonymous inner class
# Error:(com.baidu.location.b) that doesn't come with an ...
-keepattributes EnclosingMethod

#指定代码的压缩级别
-optimizationpasses 5

#包名不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
#-dontoptimize

 #预校验
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment

#忽略警告
-ignorewarning

#####################记录生成的日志数据,gradle build时在本项目根目录输出-start################

#apk 包内所有 class 的内部结构
-dump class_files.txt

#未混淆的类和成员
-printseeds seeds.txt

#列出从 apk 中删除的代码
-printusage unused.txt

#混淆前后的映射
-printmapping mapping.txt

#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################


################混淆保护自己项目的部分代码以及引用的第三方jar包library#########################
#-keep class com.baidu.** {*;}
#-keep class vi.com.** {*;}
#-dontwarn com.baidu.**

#-keepdirectories libs
#-libraryjars /umeng-analytics-v5.6.4.jar
#-libraryjars libs/android-async-http.jar
#-libraryjars libs/universal-image-loader-1.9.2-with-sources.jar
#-libraryjars libs/volley.jar
#-libraryjars libs/jpush-android-2.1.7.jar
#-libraryjars libs/zxing.jar
#-libraryjars libs/sun.misc.BASE64Decoder.jar
#快信中的包
#-libraryjars libs/ZipFileWithPwd.jar
#-libraryjars libs/encrypt.jar
#-libraryjars libs/mta-sdk-1.6.2.jar
#-libraryjars libs/sqlcipher.jar
#-libraryjars libs/guava-r09.jar
#-libraryjars libs/nineoldandroids-2.4.0.jar
#-libraryjars libs/weibosdkcore.jar
#-libraryjars libs/commons-codec.jar
#-libraryjars libs/libammsdk.jar
#-libraryjars libs/open_sdk.jar

#gson
#-libraryjars libs/gson-2.2.4.jar
-keepcl

-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# 如果使用了WebView
# webview + js
-keepattributes *JavascriptInterface*
# keep 使用 webview 的类
-keepclassmembers class  com.veidy.activity.WebViewActivity {
   public *;
}
# keep 使用 webview 的类的所有的内部类
-keepclassmembers  class  com.veidy.activity.WebViewActivity$*{
    *;
}

#三星应用市场需要添加:sdk-v1.0.0.jar,look-v1.0.1.jar
#-libraryjars libs/sdk-v1.0.0.jar
#-libraryjars libs/look-v1.0.1.jar

#我是以libaray的形式引用了一个图片加载框架,如果不想混淆 keep 掉
-keep class com.nostra13.universalimageloader.** { *; }

#友盟
#-keep class com.umeng.**{*;}

#支付宝
#-keep class com.alipay.android.app.IAliPay{*;}
#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.lib.ResourceMap{*;}


#信鸽推送
#-keep class com.tencent.android.tpush.**  {* ;}
#-keep class com.tencent.mid.**  {* ;}


#自己项目特殊处理代码

#忽略警告
#-dontwarn com.veidy.mobile.common.**
#保留一个完整的包
#-keep class com.veidy.mobile.common.** {
#    *;
# }

#-keep class  com.veidy.activity.login.WebLoginActivity{*;}
#-keep class  com.veidy.activity.UserInfoFragment{*;}
#-keep class  com.veidy.activity.HomeFragmentActivity{*;}
#-keep class  com.veidy.activity.CityActivity{*;}
#-keep class  com.veidy.activity.ClinikActivity{*;}

#如果引用了v4或者v7包
-dontwarn android.support.**

############混淆保护自己项目的部分代码以及引用的第三方jar包library-end##################

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#避免混淆泛型 如果混淆报错建议关掉
-keepattributes Signature

#移除log 测试了下没有用还是建议自己定义一个开关控制是否输出日志
#使用这个配置时，一定要注意 -dontoptimize，配置。
#dontoptimize 不要优化；将会会关闭优化，导致日志语句不会被优化掉。所以不能有这个配置
#-assumenosideeffects class android.util.Log {
#    public static boolean isLoggable(java.lang.String, int);
#    public static int v(...);
#    public static int i(...);
#    public static int w(...);
#    public static int d(...);
#    public static int e(...);
#}
#混淆代码时默认会去掉class文件中的调试信息（源码的行号、源文件信息等），
#需要在混淆配置文件中申明保持这些信息：
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable