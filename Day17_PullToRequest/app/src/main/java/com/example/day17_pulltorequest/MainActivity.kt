package com.example.day17_pulltorequest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.day17_pulltorequest.adapter.ProductsAdapter
import com.example.day17_pulltorequest.databinding.ActivityMainBinding
import com.example.day17_pulltorequest.model.ProductsModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val products = ArrayList<ProductsModel>()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        // setup data
        products.add(ProductsModel("YunTing 1"))
        productsAdapter = ProductsAdapter(products)
        // setup recyclerView
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = productsAdapter
        // setup refreshLayout
        binding.productsRefreshLayout.setProgressViewOffset(true, 50, 100)
        // size
        binding.productsRefreshLayout.setSize(SwipeRefreshLayout.LARGE)
        // 啟動下拉刷新
        binding.productsRefreshLayout.isEnabled = true
        // 監聽下拉刷新
        binding.productsRefreshLayout.setOnRefreshListener(productsRefreshLayoutListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val productsRefreshLayoutListener = SwipeRefreshLayout.OnRefreshListener {
        // 模擬加載時間
        Thread.sleep(200)

        val newProducts = ArrayList<ProductsModel>()
        // 增加內容
        for (i in 0..9) {
            val randomNumber = Random.nextInt(100)
            newProducts.add(ProductsModel("YunTing $randomNumber"))
        }
        productsAdapter.products = newProducts

        // 刷新畫面
        productsAdapter.notifyDataSetChanged()
        // 停止下拉畫面
        binding.productsRefreshLayout.isRefreshing = false
    }

}