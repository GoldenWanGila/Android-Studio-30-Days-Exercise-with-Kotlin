package com.example.day20_activitytransition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.day20_activitytransition.databinding.ActivityMainBinding
import android.app.ActivityOptions.makeSceneTransitionAnimation as makeSceneTransitionAnimation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.buttonLoveAndPeace.setOnClickListener(buttonLoveAndPeaceClickListener)
        binding.buttonExplode.setOnClickListener(buttonExplodeClickListener)
        binding.buttonSlide.setOnClickListener(buttonSlideClickListener)
        binding.buttonFade.setOnClickListener(buttonFadeClickListener)
    }

    private val buttonLoveAndPeaceClickListener = View.OnClickListener {
        val intent = Intent(this, NativeDetailActivity::class.java)
        val shareView1 = binding.imageViewYunTing as View
        val shareView2 = binding.buttonLoveAndPeace as View

        val pair1 = Pair(shareView1, "yunTingTransition")
        val pair2 = Pair(shareView2, "textTransition")
        val transitionActivityOptions = makeSceneTransitionAnimation(this, pair1, pair2)

        startActivity(intent, transitionActivityOptions.toBundle())
    }
    private val buttonExplodeClickListener = View.OnClickListener {
        startTransitionWithFlag("explode")
    }
    private val buttonSlideClickListener = View.OnClickListener {
        startTransitionWithFlag("slide")
    }
    private val buttonFadeClickListener = View.OnClickListener {
        startTransitionWithFlag("fade")
    }

    private fun startTransitionWithFlag(flag: String) {
        val intent = Intent(this, NativeDetailActivity::class.java)
        intent.putExtra("flag", flag)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }
}