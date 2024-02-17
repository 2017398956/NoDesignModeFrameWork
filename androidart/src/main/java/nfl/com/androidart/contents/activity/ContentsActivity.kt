package nfl.com.androidart.contents.activity

import android.os.Bundle
import android.view.View
import com.nfl.libraryoflibrary.utils.ToastTool
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity
import com.nfl.libraryoflibrary.view.recyclerview.CustomRecyclerView
import nfl.com.androidart.contents.adapter.ContentsAdapter
import nfl.com.androidart.contents.databinding.Contents
import nfl.com.androidart.contents.handing.ContentsViewController
import nfl.com.androidart.databinding.ActivityContentsBinding
import nfl.com.androidart.utils.ActivityLauncher

/**
 * 《Android 开发艺术探索》
 */
class ContentsActivity : CommonActionBarActivity() {

    private lateinit var binding: ActivityContentsBinding
    private var contents: Contents? = null
    private var contentsViewController: ContentsViewController? = null
    private var contentsAdapter: ContentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(null)
        actionBarTitle = "《Android 开发艺术探索》"
        binding = ActivityContentsBinding.inflate(layoutInflater, ll_pad_container, true)
        binding.bnTest.setOnClickListener(ContentsViewController())
        initData()
    }

    private fun initData() {
        contents = Contents()
        contents!!.chapter = "测试按钮"
        // 添加目录
        val titles = arrayOf(
            "第 1 章 Activity 的生命周期和启动模式", "第 2 章 IPC 机制",
            "第 3 章 View 的事件体系", "第 4 章",
            "第 5 章", "第 6 章", "第 7 章",
            "第 8 章  理解 Window 和 WindowManager", "第 9 章", "第 10 章 Android 的消息机制",
            "第 11 章", "第 12 章", "第 13 章",
            "第 14 章", "第 15 章"
        )
        val data: MutableList<Contents> = ArrayList()
        for (title in titles) {
            contents = Contents()
            contents!!.chapter = title
            data.add(contents!!)
        }
        contentsAdapter = ContentsAdapter(this, data)
        binding.rvContents.adapter = contentsAdapter
        binding.rvContents.addOnItemClickListener(onItemClickListener)
    }

    private val onItemClickListener: CustomRecyclerView.OnItemClickListener =
        object : CustomRecyclerView.OnItemClickListener() {
            override fun onClick(view: View, position: Int) {
                super.onClick(view, position)
                ToastTool.showShortToast(position.toString() + "")
                ActivityLauncher.launchChapter(this@ContentsActivity, position)
            }
        }
}
