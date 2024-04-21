package com.a2017398956.nodesignmodeframework.activity

import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.hardware.display.IDisplayManager
import android.hardware.display.VirtualDisplay
import android.os.Build.VERSION
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.DisplayInfo
import android.view.IRotationWatcher
import android.view.IWindowManager
import android.view.Surface
import com.a2017398956.nodesignmodeframework.databinding.ActivityScreenShotBinding
import com.nfl.libraryoflibrary.utils.screenshot.ScreenShotUtil
import com.nfl.libraryoflibrary.view.BaseActivity
import java.lang.reflect.Method


class ScreenShotActivity : BaseActivity() {

    private lateinit var binding: ActivityScreenShotBinding
    private lateinit var mDisplay: Display
    private val mDisplayMetrics = DisplayMetrics()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(null)
        binding = ActivityScreenShotBinding.inflate(layoutInflater, ll_pad_container, true)
        mDisplay = windowManager.defaultDisplay
        mDisplay.getRealMetrics(mDisplayMetrics)
        ScreenShotUtil.init()
        binding.btnScreenShot.setOnClickListener {
            try {
                ScreenShotUtil.screenshot()?.let {
                    binding.ivScreenShot.setImageBitmap(it)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}