package com.example.day14_ball.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.day14_ball.databinding.ActivityAnimatorSetBinding

class AnimatorSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimatorSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimatorSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.oneByOneButton.setOnClickListener(oneByOneButtonClickHandler)
        binding.sameTimeButton.setOnClickListener(sameTimeButtonClickHandler)
    }

    private val oneByOneButtonClickHandler = View.OnClickListener {
        val animator1 = ObjectAnimator.ofFloat(binding.volleyBall1, "translationY", 0f, -800f, 0f)
        val animator2 = ObjectAnimator.ofFloat(binding.volleyBall2, "translationY", 0f, 800f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 600
        animatorSet.play(animator2).after(animator1)
        animatorSet.start()
    }
    private val sameTimeButtonClickHandler = View.OnClickListener {
        val animator1 = ObjectAnimator.ofFloat(binding.volleyBall1, "translationY", 0f, -800f, 0f)
        val animator2 = ObjectAnimator.ofFloat(binding.volleyBall2, "translationY", 0f, 800f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 600
        animatorSet.playTogether(animator1, animator2)
        animatorSet.start()
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