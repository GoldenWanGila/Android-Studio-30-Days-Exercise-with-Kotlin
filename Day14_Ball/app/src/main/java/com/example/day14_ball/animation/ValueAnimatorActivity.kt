package com.example.day14_ball.animation

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.day14_ball.databinding.ActivityValueAnimatorBinding

class ValueAnimatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValueAnimatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValueAnimatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.goButton.setOnClickListener(goButtonClickHandler)
    }

    private val goButtonClickHandler = View.OnClickListener {
        val animator = ValueAnimator.ofFloat(0f, -400f, 0f)
        animator.duration = 600

        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator?.animatedValue as Float
            binding.volleyBall.translationY = value
        }

        animator.start()
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