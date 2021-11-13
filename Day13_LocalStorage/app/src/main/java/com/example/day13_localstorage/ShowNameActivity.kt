package com.example.day13_localstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.example.day13_localstorage.databinding.ActivityShowNameBinding

class ShowNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.showNameButton.setOnClickListener(showNameButtonHandler)
    }

    private val showNameButtonHandler = View.OnClickListener {
        // 從 local storage 存取 剛剛輸入的 name
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val name = preference.getString("login_name", "")
        if (name.isNullOrEmpty()) { // 如果還沒有輸入，則跳出提示
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show()
        }
        binding.showNameTextView.text = name
    }
    // 當返回鍵被按下
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