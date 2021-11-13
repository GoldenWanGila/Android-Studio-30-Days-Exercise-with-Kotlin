package com.example.day20_activitytransition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.MenuItem
import android.view.Window
import com.example.day20_activitytransition.databinding.ActivityNativeDetailBinding

class NativeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeDetailBinding.inflate(layoutInflater)

        setupTransition()
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTransition() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        when (intent.getStringExtra("flag")) {
            "explode" -> {
                val transition = Explode()
                transition.duration = 1000
                window.enterTransition = transition
                window.exitTransition = transition
            }
            "slide" -> {
                val transition = Slide()
                transition.duration = 1000
                window.enterTransition = transition
                window.exitTransition = transition
            }
            "fade" -> {
                val transition = Fade()
                transition.duration = 1000
                window.enterTransition = transition
                window.exitTransition = transition
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}