package com.example.day13_localstorage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.day13_localstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        hideSoftInput()

        binding.saveButton.setOnClickListener(saveButtonHandler)
        binding.nextButton.setOnClickListener(nextButtonHandler)
    }

    private val saveButtonHandler = View.OnClickListener {
        val name = binding.editText.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, "please type name", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        // 將輸入的 name 存入 local storage
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preference.edit()
        editor.putString("login_name", name)
        editor.apply()
        // 跳出成功提醒並清除輸入欄
        Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show()
        clearEditText()
    }
    private val nextButtonHandler = View.OnClickListener {
        val intent = Intent(this, ShowNameActivity::class.java)
        startActivity(intent)
    }

    private fun hideSoftInput() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }

    private fun clearEditText() {
        binding.editText.text = null
    }
}