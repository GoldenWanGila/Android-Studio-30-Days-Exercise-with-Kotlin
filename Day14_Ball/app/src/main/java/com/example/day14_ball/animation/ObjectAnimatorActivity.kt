package com.example.day14_ball.animation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.day14_ball.databinding.ActivityObjectAnimatorBinding

class ObjectAnimatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObjectAnimatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectAnimatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.goButton.setOnClickListener(goButtonClickHandler)
    }

    private val goButtonClickHandler = View.OnClickListener {
        val animator = ObjectAnimator.ofFloat(binding.volleyBall, "rotationY", 0f, 360f)
        animator.duration = 500
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