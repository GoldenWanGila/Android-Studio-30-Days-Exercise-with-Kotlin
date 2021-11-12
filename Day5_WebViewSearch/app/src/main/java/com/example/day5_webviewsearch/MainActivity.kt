package com.example.day5_webviewsearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val webView = findViewById<WebView>(R.id.webView)
            val webViewClient = WebViewClient()
            val url = "https://google.com.tw"
            val button = findViewById<Button>(R.id.search_button)
            val editText = findViewById<EditText>(R.id.search_editText)

        webView.webViewClient = webViewClient
        webView.loadUrl(url)

        button.setOnClickListener{
            webView.loadUrl("$url/search?q=${editText.text}")
            hideSoftInput(editText)
        }
    }

    private fun hideSoftInput(editText: EditText) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}