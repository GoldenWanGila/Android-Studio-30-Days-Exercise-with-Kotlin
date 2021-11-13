package com.example.day10_progresscontrol

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.day10_progresscontrol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentProgress: Int = 0
    private val maxProcess: Int = 100
    private val repeatTaskTime: Long = 500
    private val increaseBias: Int = 10

    private val taskHandler = Handler(Looper.getMainLooper())
    private val runnable = Runnable { startIncreaseProgress() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        // 設定 每一個 button 的 ClickListener
        binding.startButton.setOnClickListener(startButtonHandler)
        binding.pauseButton.setOnClickListener(pauseButtonHandler)
        binding.stopButton.setOnClickListener(stopButtonHandler)
        // 初始化進度條
        binding.progressBar.max = maxProcess
        binding.progressBar.progress = currentProgress
    }

    private val startButtonHandler = View.OnClickListener { startIncreaseProgress() }
    private val pauseButtonHandler = View.OnClickListener { pauseIncreaseProgress() }
    private val stopButtonHandler = View.OnClickListener { resetProgress() }

    private fun startIncreaseProgress() {
        if (currentProgress >= maxProcess) {    // 如果目前的進度超過了限制的最大值，則停止增加進度
            pauseIncreaseProgress()
            return
        } else {                                // 如果目前的進度小於限制的最大值，則繼續增加進度
            increaseProgressBy()
        }
        // 進度條每隔一段時間增加的主要功臣
        // runnable -> 放入每個一段時間要執行的工作
        // repeatTaskTime -> 間隔時間
        taskHandler.postDelayed(runnable, repeatTaskTime)
    }

    private fun pauseIncreaseProgress() {
        // 關閉 thread -> 使進度條增加的工作停止
        taskHandler.removeCallbacksAndMessages(null)
    }

    private fun resetProgress() {
        pauseIncreaseProgress()

        currentProgress = 0
        binding.progressBar.progress = currentProgress

        updateProgressTextView()
    }

    private fun increaseProgressBy() {
        currentProgress += increaseBias
        binding.progressBar.progress = currentProgress

        updateProgressTextView()
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressTextView() {
        binding.progressTextView.text = "$currentProgress%"
    }
}