package com.example.day2_discount

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.ceil

/* objects
    1. SeekBar 事件處理
    2. EditText 事件處理
*/

var discount = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekBar = findViewById<SeekBar>(R.id.discountBar)
        // SeekBar 的事件監聽 function
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            // 進度條開始拖動事件
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            // 進度條停止拖動事件
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            // 進度條改變事件
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                discount = progress
                showValue(findViewById(R.id.discount))
                showValue(findViewById(R.id.sellPrice))
            }
        })
        val editText = findViewById<EditText>(R.id.money)
        // EditText 的是鍵監聽 function
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {       // IME_ACTION_DONE-->按下鍵盤的完成鍵時，鍵盤傳出的 actionId
                showValue(findViewById(R.id.sellPrice))         // 當按下完成鍵時，顯示目前原價於 sellPrice
            }
            false   // 返回 false, 隱藏鍵盤；返回 true, 保留鍵盤。
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showValue(view: TextView) {
        if (view.id == R.id.discount) {
            view.text = "打折：$discount%"
        }
        else if (view.id == R.id.sellPrice) {
            val originPrice: Int = findViewById<EditText>(R.id.money).text.toString().toInt()
            val sellPrice: Int = originPrice-(ceil(discount*originPrice*0.01).toInt())
            view.text = "價格：$sellPrice"
        }
    }
}