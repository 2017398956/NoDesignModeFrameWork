package com.a2017398956.nodesignmodeframework.activity

import android.os.Bundle
import android.util.Log
import com.a2017398956.nodesignmodeframework.databinding.ActivityHeatMapBinding
import com.nfl.libraryoflibrary.view.BaseActivity
import com.nfl.libraryoflibrary.view.heatmap.HeatMap
import com.nfl.libraryoflibrary.view.heatmap.HeatMapOverlay
import com.nfl.libraryoflibrary.view.heatmap.WeightedLatLng


class HeatMapActivity : BaseActivity() {

    private lateinit var binding: ActivityHeatMapBinding
    private var imageWidth = 0
    private var imageHeight = 0
    private lateinit var heatMap: HeatMap
    private lateinit var heatMapOverlay: HeatMapOverlay
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(null)
        binding = ActivityHeatMapBinding.inflate(layoutInflater, ll_pad_container, true)
        binding.fab1.setOnClickListener {
            val startTime = System.currentTimeMillis()
            Log.d(TAG, "image1 startTime:$startTime")
            val data = generateHeatMapData()
            heatMap.setWeightedData(data)
            binding.image.setImageBitmap(heatMap.generateMap())
            val endTime = System.currentTimeMillis()
            Log.d(TAG, "image1 cost time:${endTime - startTime}")
        }
        binding.fab2.setOnClickListener {
            val startTime = System.currentTimeMillis()
            Log.d(TAG, "image2 startTime:$startTime")
            val data = generateHeatMapData()
            var endTime = System.currentTimeMillis()
            Log.d(TAG, "image2 generateHeatMapData cost time:${endTime - startTime}")
            heatMapOverlay.setWeightedData(data)
            binding.image.setImageBitmap(heatMapOverlay.generateMap())
            endTime = System.currentTimeMillis()
            Log.d(TAG, "image2 cost time:${endTime - startTime}")
        }
        binding.image.post {
            imageWidth = binding.image.width
            imageHeight = binding.image.height
            val data = generateHeatMapData()
            heatMap =
                HeatMap.Builder().weightedData(data).radius(35).width(imageWidth).height(imageHeight)
                    .build()
            heatMapOverlay = HeatMapOverlay.Builder().weightedData(data).radius(35).width(imageWidth)
                .height(imageHeight).build()
            binding.image.setImageBitmap(heatMap.generateMap())
        }
    }

    private fun generateHeatMapData(): List<WeightedLatLng> {
        val data: MutableList<WeightedLatLng> = ArrayList()
        for (i in 0..32*32) {
            data.add(
                WeightedLatLng(
                    (Math.random() * imageWidth).toInt(), (Math.random() * imageHeight).toInt(),
                    Math.random() * 100
                )
            )
        }
        return data
    }

    companion object {
        const val TAG = "HeatMapActivity"
    }
}