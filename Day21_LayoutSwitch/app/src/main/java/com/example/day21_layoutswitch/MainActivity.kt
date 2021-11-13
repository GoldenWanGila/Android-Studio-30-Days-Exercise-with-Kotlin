package com.example.day21_layoutswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day21_layoutswitch.adapter.GridLayoutItemAdapter
import com.example.day21_layoutswitch.adapter.LinearLayoutItemAdapter
import com.example.day21_layoutswitch.databinding.ActivityMainBinding
import com.example.day21_layoutswitch.model.ItemModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isLinearLayout = false
    private var items = ArrayList<ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupItems()

        val layoutManager = GridLayoutManager(this, 2)
        val itemAdapter = GridLayoutItemAdapter(items)

        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = layoutManager
    }

    // 將 menu 替換成新定義好的 menu_main
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 當點擊 layout switcher 時，要做出的對應行動
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_layout_switcher) {
            isLinearLayout = !isLinearLayout
            switchLayout()
            switchIcon(item)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupItems() {
        items.add(ItemModel("Surprised YunTing", 100, 1, R.drawable.yun_ting_1))
        items.add(ItemModel("YunTing with different glasses", 200, 1, R.drawable.yun_ting_2))
        items.add(ItemModel("YunTing with pigtails", 300, 1, R.drawable.yun_ting_3))
        items.add(ItemModel("YunTing with big smile", 400, 1, R.drawable.yun_ting_4))
    }

    private fun switchLayout() {
        when (isLinearLayout) {
            true -> {
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = LinearLayoutItemAdapter(items)
            }
            false -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
                binding.recyclerView.adapter = GridLayoutItemAdapter(items)
            }
        }
    }

    private fun switchIcon(item: MenuItem) {
        item.icon = (
                if (isLinearLayout)
                    ContextCompat.getDrawable(this, R.drawable.ic_grid)
                else
                    ContextCompat.getDrawable(this, R.drawable.ic_linear)
                )
    }
}