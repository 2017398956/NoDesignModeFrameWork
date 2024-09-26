package com.a2017398956.nodesignmodeframework.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.a2017398956.nodesignmodeframework.R
import com.nfl.libraryoflibrary.view.MeetingProfilePictureListView

class MeetingActivity : AppCompatActivity() {

    private lateinit var mpplvTest: MeetingProfilePictureListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting)
        mpplvTest = findViewById(R.id.mpplv_test)
        val data = arrayListOf<Int>()
        for (i in 0 until 28) {
            data.add(i)
        }
        val adapter = object : MeetingProfilePictureListView.BaseAdapter(false, data) {
            override fun getCount(): Int {
                return data.size
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val imageView = ConstraintLayout(parent.context)
                LayoutInflater.from(parent.context).inflate(R.layout.item_anim, imageView, true)
                imageView.findViewById<TextView>(R.id.tv_content).text = "${position}"
                imageView.setBackgroundColor(Color.RED - 100 * position)
                return imageView
            }
        }
        mpplvTest.adapter = adapter
        mpplvTest.onItemScaleChangeListener =
            object : MeetingProfilePictureListView.OnItemScaleChangeListener {
                override fun onItemScaleChanged(position: Int, normal: Boolean) {
                    Log.d("TAG", "position:$position normal:$normal")
                }
            }
    }
}