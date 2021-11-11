package com.example.day1_tapcounter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/* objects
    1. 自訂 OptionsMenu：用於實踐歸零按鈕(右上角的按鈕)
    2. 計數按鈕
    3. 歸零按鈕
*/

var number = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 獲取 tapButton，並以同名常數存取
        val tapButton: Button = findViewById(R.id.tapButton)

        // 設定 tapButton 被按下之後要處理的事
        tapButton.setOnClickListener {
            plusAndShowNumber(findViewById(R.id.counter))
        }
    }

    private fun plusAndShowNumber(counter: TextView) {
        number++
        counter.text = number.toString()
    }

    private fun resetNumber(counter: TextView) {
        number = 0
        counter.text = number.toString()
    }

    // 覆寫 onCreateOptionsMenu function；onCreateOptionsMenu(menu: Menu)-->用於初始化選單，menu為即將要顯示的 Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // this.menuInflater.inflate()-->獲取要顯示 menu item
        // menuInflater-->用於獲取一個 MenuInflater；inflate(佈局檔案中定義的 menu, menu)-->把(佈局檔案中定義的 menu)載入給第二個引數中的menu
        this.menuInflater.inflate(R.menu.menu_main, menu)
        // return true：顯示此 menu
        // return false：不顯示
        return true
    }

    //覆寫 onOptionsItemSelected function：onOptionsItemSelected(item: MenuItem)-->選單項(item)被點擊時呼叫的function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.resetItem) {
            resetNumber(findViewById(R.id.counter))
        }
        return true
    }
}