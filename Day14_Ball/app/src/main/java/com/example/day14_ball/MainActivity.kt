package com.example.day14_ball

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day14_ball.animation.AnimatorSetActivity
import com.example.day14_ball.animation.InterpolatorActivity
import com.example.day14_ball.animation.ObjectAnimatorActivity
import com.example.day14_ball.animation.ValueAnimatorActivity
import com.example.day14_ball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MainAdapter { position: Int -> tapHandler(position) }
    }

    private fun tapHandler(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this, ValueAnimatorActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this, ObjectAnimatorActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this, AnimatorSetActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(this, InterpolatorActivity::class.java)
                startActivity(intent)
            }

        }
    }
}