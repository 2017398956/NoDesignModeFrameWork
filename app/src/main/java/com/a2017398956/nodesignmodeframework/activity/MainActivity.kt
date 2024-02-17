package com.a2017398956.nodesignmodeframework.activity

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.lifecycle.lifecycleScope
import com.a2017398956.nodesignmodeframework.activity.ActivityTranslateAnimator.AActivity
import com.a2017398956.nodesignmodeframework.databinding.ActivityMainBinding
import com.a2017398956.nodesignmodeframework.exception.test.ResultInfoExceptionActivity
import com.a2017398956.nodesignmodeframework.pushtoloadmore.PushLoadMoreActivity
import com.google.android.material.snackbar.Snackbar
import com.nfl.libraryoflibrary.R
import com.nfl.libraryoflibrary.constant.ApplicationContext
import com.nfl.libraryoflibrary.utils.ExecShell
import com.nfl.libraryoflibrary.utils.ImageTools
import com.nfl.libraryoflibrary.utils.LogTool
import com.nfl.libraryoflibrary.utils.RootDetectorTool
import com.nfl.libraryoflibrary.utils.ToastTool
import com.nfl.libraryoflibrary.utils.image.ImageLoadTool
import com.nfl.libraryoflibrary.view.BaseActivity
import com.nfl.libraryoflibrary.view.db_insight.DBInsightActivity
import com.nfl.libraryoflibrary.view.floatwindow.FloatWindowActivity
import com.nfl.libraryoflibrary.view.traffic_float_window.TrafficFloatWindowActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nfl.com.androidart.utils.ActivityLauncher
import personal.nfl.permission.annotation.GetPermissions4AndroidX

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10
    var permissions = arrayOf(Manifest.permission.READ_CONTACTS)
    var activity: Activity = this

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                2 -> {}
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(null)
        binding = ActivityMainBinding.inflate(layoutInflater, ll_pad_container, true)
        fileSystemDescription()
        LogTool.i("cmd: " + ExecShell().executeCommand(arrayOf("ls")))
        handler.sendEmptyMessageDelayed(2, 2000)
        hiddenBackIcon()
        actionBarTitle = "主页"
        loadNetworkImage()
        setListeners()
        printRootInfo()
        Snackbar.make(binding.constraintLayout, "Test snackbar", Snackbar.LENGTH_LONG)
            .setAction(
                "action"
            ) {
                ToastTool.showShortToast("Action");
            }.show()
        // ViewFinder.inject(this);
        openNotification(this)
    }

    private fun openNotification(context: Context) {
        // startService(new Intent(this, NotificationReceiverService.class));
        handler.postDelayed({
            val contextIntent = PendingIntent.getActivity(
                context, 0,
                intent, 0
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            var notification: Notification? = null
            val id = "channel_001"
            val name = "name"
            val action = ""
            notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //判断API
                val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW)
                notificationManager.createNotificationChannel(mChannel)
                Notification.Builder(context)
                    .setChannelId(id)
                    .setContentTitle("通知")
                    .setContentText("你有一个新的通知")
                    .setContentIntent(contextIntent)
                    .setLights(Color.GREEN, 1000, 1000) //设置三色灯
                    .setSmallIcon(R.drawable.icon_install).build()
            } else {
                NotificationCompat.Builder(context)
                    .setContentTitle("通知")
                    .setContentText("你有新的通知")
                    .setSmallIcon(R.drawable.icon_install)
                    .setLargeIcon(ImageTools.drawableToBitmap(resources.getDrawable(R.drawable.icon_install)))
                    .setOngoing(true)
                    .setContentIntent(contextIntent)
                    .setLights(Color.GREEN, 1000, 1000) //设置三色灯
                    .setChannelId(id).build() //无效
            }
            notification.flags = Notification.FLAG_ONGOING_EVENT // 设置常驻 Flag
            notificationManager.notify(1, notification)
            LogTool.i("已发送通知")
        }, 5000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if (grantResults.isNotEmpty()) {
                    val notGrantedPermissionList: MutableList<String> = ArrayList()
                    for (i in 0 until grantResults.size) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            notGrantedPermissionList.add(permissions[i])
                        }
                    }
                    if (0 == notGrantedPermissionList.size) {
                        // TODO : 被注解的方法
                    } else {
                        // TODO:暂时只判断第一个未授权的权限
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                notGrantedPermissionList[0]
                            )
                        ) {
                            // 没有权限，被拒绝过至少一次且没有点击 再不提醒
                            ActivityCompat.requestPermissions(
                                activity,
                                notGrantedPermissionList.toTypedArray(),
                                0
                            )
                        } else {
                            // TODO :根据 permissionList 弹出提示框，以手动授权
                        }
                    }
                }
            }
        }
    }

    private fun loadNetworkImage() {
        val imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg"
        val imageUrl2 = "http://10.11.18.40/demo/38F843A7BE1AC297250F88FBF79D8DF3.png"
        ImageLoadTool.loadImage(this, imageUrl, binding.iv01, 0, null);
        ImageLoadTool.loadImage(this, imageUrl2, binding.iv02, 0, null);
    }

    private fun setListeners() {
        binding.bnResultException.setOnClickListener {
            startActivity(Intent(this, ResultInfoExceptionActivity::class.java))
        }
        binding.llLeftSliding.setDisplayedViewOnClickListener {
            ToastTool.showShortToast("ll_displayed")
        }
        binding.tv01.setOnClickListener {
            ToastTool.showShortToast("tv_01")
        }
        binding.tv02.setOnClickListener {
            ToastTool.showShortToast("tv_02")
        }
        binding.bnBottomSheetBehavior.setOnClickListener {
            startActivity(Intent(this, BottomSheetBehaviorTestActivity::class.java))
        }
        binding.bnPtrsml.setOnClickListener {
            startActivity(Intent(this, PullToRefreshSwipeMenuListViewActivity::class.java))
        }
        binding.bnCrv.setOnClickListener {
            startActivity(Intent(this, PushLoadMoreActivity::class.java))
        }
        binding.bnRecyclerView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
        binding.bnValueAnimator.setOnClickListener {
            startActivity(Intent(this, ValueAnimatorTestActivity::class.java))
        }
        binding.bnImitationWin10.setOnClickListener {
            startActivity(Intent(this, ImitationWin10ProgressBarActivity::class.java))
        }
        binding.bnImitationWechat.setOnClickListener {
            startActivity(Intent(this, TestImitatationWeChatActivity::class.java))
        }
        binding.bnFloatWindow.setOnClickListener {
            startActivity(Intent(this, FloatWindowActivity::class.java))
        }
        binding.bnLiuLang.setOnClickListener {
            startActivity(Intent(this, TrafficFloatWindowActivity::class.java))
        }
        binding.bnDatabase.setOnClickListener {
            startActivity(Intent(this, DBInsightActivity::class.java))
        }
        binding.bnTranslateAnimator.setOnClickListener {
            startActivity(Intent(this, AActivity::class.java))
        }
        binding.bnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
        binding.bnFingerprint.setOnClickListener {
            startFingerprintTestActivity()
        }
        binding.bnPedometer.setOnClickListener {
            startActivity(Intent(this, PedometerActivity::class.java))
        }
        binding.bnTinker.setOnClickListener {
            startActivity(Intent(this, TinkerTestActivity::class.java))
        }
        binding.bnPictureSelector.setOnClickListener {
            startActivity(Intent(this, PictureSelectorActivity::class.java))
        }
        binding.bnAndroidArt.setOnClickListener {
            ActivityLauncher.launchContentsActivity(this)
        }
        binding.bnHeaderGridView.setOnClickListener {
            startActivity(Intent(this, HeaderGridViewActivity::class.java))
        }
    }

    @GetPermissions4AndroidX(*[Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    private fun startFingerprintTestActivity() {
        startActivity(Intent(this, FingerprintTestActivity::class.java))
    }

    private fun printRootInfo() {
        lifecycleScope.launch(Dispatchers.Default) {
            val filterInfos: MutableList<String> = ArrayList() // 判断是否有 root 可能的关键词
            filterInfos.add("root")
            val rootDetailInfo =
                RootDetectorTool(ApplicationContext.applicationContext, filterInfos).rootDetailInfo
            withContext(Dispatchers.Main) {
                binding.tvTestInfo.text = rootDetailInfo
            }
        }
    }

    private fun fileSystemDescription() {
        // /data
        LogTool.i("Environment.getDataDirectory():" + Environment.getDataDirectory().absolutePath)
        // Environment.getRootDirectory()
        LogTool.i("Environment.getRootDirectory():" + Environment.getRootDirectory().absolutePath)
        // /cache
        LogTool.i("Environment.getDownloadCacheDirectory():" + Environment.getDownloadCacheDirectory().absolutePath)
        /*
         * /storage/emulated/0/Movies（根据不同的版本会有差异，多张内置卡是不是编号从 0 到 n ？）这个路径是真实的 SDCard（手机内置的 SDCard）路径；
         * /storage/self/primary/Movies 也等效于 /storage/self/primary ，但 primary 是对 /storage/emulated/0 的引用，是 link 类型的，有关内容参考 Linux 文件引用；
         * /storage/131A-2419（也可能是别的名称，这个目录都是真实路径） 下的文件夹目录和 /storage/emulated/0 相同，一般是手机插的 TF 卡；
         * 另外，/sdcard 是对 /storage/emulated/0 的引用，不是真正的文件。
         */LogTool.i(
            "Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES):" + Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES
            ).absolutePath
        )
        LogTool.i(
            "外置存储卡：" + (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).parentFile?.parentFile?.parentFile?.listFiles()
                ?.get(0)?.absolutePath
                ?: "没有权限")
        )
    }

    private fun cancelNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(1)
    }

    companion object {
        const val TAG = "MainActivity"
    }

}
