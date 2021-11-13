package com.example.day14_ball.animation

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.example.day14_ball.databinding.ActivityInterpolatorBinding

class InterpolatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterpolatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterpolatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    @SuppressLint("Recycle")
    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val animator = ObjectAnimator.ofFloat(binding.volleyBall, "translationX", 0f, 600f, 0f)
        animator.duration = 600

        binding.cycleButton.setOnClickListener { cycleButtonClickHandler(animator) }
        binding.accelerateButton.setOnClickListener { accelerateButtonClickHandler(animator) }
        binding.accelerateDecelerateButton.setOnClickListener {
            accelerateDecelerateButtonClickHandler(
                animator
            )
        }
        binding.anticipateButton.setOnClickListener { anticipateButtonClickHandler(animator) }
        binding.anticipateOvershootButton.setOnClickListener {
            anticipateOvershootButtonClickHandler(
                animator
            )
        }
        binding.bounceButton.setOnClickListener { bounceButtonClickHandler(animator) }
        binding.decelerateButton.setOnClickListener { decelerateButtonClickHandler(animator) }
        binding.linearButton.setOnClickListener { linearButtonClickHandler(animator) }
        binding.overshootButton.setOnClickListener { overshootButtonClickHandler(animator) }
    }

    private fun cycleButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = CycleInterpolator(2.0f)
        animator.start()
    }

    private fun accelerateButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

    private fun accelerateDecelerateButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }

    private fun anticipateButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator  = AnticipateInterpolator()
        animator.start()
    }

    private fun anticipateOvershootButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = AnticipateOvershootInterpolator()
        animator.start()
    }

    private fun bounceButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = BounceInterpolator()
        animator.start()
    }

    private fun decelerateButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    private fun linearButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private fun overshootButtonClickHandler(animator: ObjectAnimator) {
        animator.interpolator = OvershootInterpolator()
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