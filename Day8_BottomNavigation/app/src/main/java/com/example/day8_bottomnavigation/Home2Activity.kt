package com.example.day8_bottomnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.day8_bottomnavigation.databinding.ActivityHome2Binding

class Home2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Home 2"
        // 這是用來顯示右上角的返回按鈕
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.home2Button.setOnClickListener{
            val intent = Intent(this, Home3Activity::class.java)
            startActivity(intent)
        }
    }

    // 設定按了返回鍵之後，會回到上一個 activity
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