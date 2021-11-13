package com.example.day19_littlebirdsound

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.SeekBar
import com.example.day19_littlebirdsound.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var birdAnimator: ObjectAnimator
    private lateinit var thread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        // create mediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_bird)
        // setup animator
        setupRotateAnimator()
        // setup volume seekbar and volume text(default: 50% volume)
        setupVolumeSeekbarAndText()
        // setup thread for music progressbar
        setupThread()
        thread.start()
        // button initialization
        binding.buttonPlayAndPause.setOnClickListener(buttonPlayAndPauseClickListener)
        binding.buttonStop.setOnClickListener(buttonStopClickListener)
        // volume seekbar initialization
        binding.volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(
                seekbar: SeekBar,
                seekBarProgression: Int,
                isFromUser: Boolean
            ) {
                // update volume progression text
                binding.volumeProgressionTextView.text = "Volume: $seekBarProgression%"
                // update volume
                mediaPlayer.setVolume(seekBarProgression / 100f, seekBarProgression / 100f)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun setupRotateAnimator() {
        birdAnimator =
            ObjectAnimator.ofFloat(binding.birdDisc, "rotation", 0f, 90f, 180f, 270f, 360f)
        birdAnimator.interpolator = LinearInterpolator()
        birdAnimator.duration = 3000
        birdAnimator.repeatCount = ObjectAnimator.INFINITE
    }

    @SuppressLint("SetTextI18n")
    private fun setupVolumeSeekbarAndText() {
        binding.volumeSeekBar.progress = 50
        binding.volumeProgressionTextView.text = "Volume: ${binding.volumeSeekBar.progress}%"
        mediaPlayer.setVolume(
            binding.volumeSeekBar.progress / 100f,
            binding.volumeSeekBar.progress / 100f
        )
    }

    private fun setupThread() {
        binding.playProgressionBar.max = mediaPlayer.duration
        thread = Thread {
            while (true) {
                Thread.sleep(200)
                binding.playProgressionBar.progress = mediaPlayer.currentPosition
                if (!(mediaPlayer.isPlaying) and birdAnimator.isRunning) {
                    birdAnimator.pause()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private val buttonPlayAndPauseClickListener = View.OnClickListener { view ->

        val button = view as Button

        when (button.text) {
            "Play" -> {
                // 撥放音樂
                mediaPlayer.start()
                // bird disc 開始轉動或是恢復轉動
                if (birdAnimator.isPaused)
                    birdAnimator.resume()
                else
                    birdAnimator.start()
                button.text = "Pause"
            }
            "Pause" -> {
                // 暫停音樂(需要保留撥放進度)
                mediaPlayer.pause()
                // bird disc 暫停轉動
                birdAnimator.pause()
                button.text = "Play"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private val buttonStopClickListener = View.OnClickListener {
        // 停止音樂並初始化撥放進度
        mediaPlayer.pause()
        mediaPlayer.reset()
        // 初始化音樂進度條
        binding.playProgressionBar.progress = 0
        // 停止 bird disc 轉動並初始化它的位置
        birdAnimator.end()

        binding.buttonPlayAndPause.text = "Play"
    }
}