package com.a2017398956.nodesignmodeframework.activity

import android.os.Bundle
import com.a2017398956.nodesignmodeframework.databinding.ActivityMyPaletteBinding
import com.nfl.libraryoflibrary.utils.inflate
import com.nfl.libraryoflibrary.view.BaseActivity

class MyPaletteActivity : BaseActivity() {

    private val binding: ActivityMyPaletteBinding by inflate<ActivityMyPaletteBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnClear.setOnClickListener {
            binding.vMyPalette.clear();
        }
    }
}