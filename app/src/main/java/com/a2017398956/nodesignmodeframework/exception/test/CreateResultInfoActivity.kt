package com.a2017398956.nodesignmodeframework.exception.test

import android.app.Activity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.a2017398956.nodesignmodeframework.R
import com.a2017398956.nodesignmodeframework.databinding.ActivityCreateResultInfoBinding
import com.nfl.libraryoflibrary.utils.inflate

/**
 * Created by fuli.niu
 */
class CreateResultInfoActivity : Activity() {

    private val binding by inflate<ActivityCreateResultInfoBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBack.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}
