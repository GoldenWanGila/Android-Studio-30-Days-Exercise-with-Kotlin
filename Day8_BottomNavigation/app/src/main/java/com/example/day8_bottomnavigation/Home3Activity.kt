package com.example.day8_bottomnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.day8_bottomnavigation.databinding.ActivityHome3Binding

class Home3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHome3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHome3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Home 3"
        // 這是用來顯示右上角的返回按鈕
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.home3Button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
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