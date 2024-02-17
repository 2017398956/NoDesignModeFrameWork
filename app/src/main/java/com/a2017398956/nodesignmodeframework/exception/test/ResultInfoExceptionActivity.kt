package com.a2017398956.nodesignmodeframework.exception.test

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.a2017398956.nodesignmodeframework.R
import com.a2017398956.nodesignmodeframework.databinding.ActivityResultInfoExceptionBinding
import com.nfl.libraryoflibrary.utils.ToastTool
import com.nfl.libraryoflibrary.utils.inflate

/**
 * Created by fuli.niu
 */
class ResultInfoExceptionActivity : Activity() {

    private val binding by inflate<ActivityResultInfoExceptionBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, CreateResultInfoActivity::class.java)
            startActivityForResult(intent, 10)
        }
        binding.btnNewTask.setOnClickListener {
            val intent = Intent(this, CreateResultInfoActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivityForResult(intent, 10)
        }
    }

    @SuppressLint("LongLogTag")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode:$requestCode | resultCode:$resultCode")
        ToastTool.showLongToast("requestCode:$requestCode | resultCode is RESULT_OK? ${resultCode == RESULT_OK}")
    }

    override fun onDestroy() {
        ToastTool.showShortToast("ResultInfoExceptionActivity onDestroy")
        super.onDestroy()
    }

    companion object {
        const val TAG = "ResultInfoExceptionActivity"
    }
}
