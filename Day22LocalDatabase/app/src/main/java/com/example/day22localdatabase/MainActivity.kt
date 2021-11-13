package com.example.day22localdatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day22localdatabase.adapter.ItemAdapter
import com.example.day22localdatabase.databinding.ActivityMainBinding
import com.example.day22localdatabase.model.ItemModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: MemberDatabaseHelper
    private lateinit var adapter: ItemAdapter
    private var items = ArrayList<ItemModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = MemberDatabaseHelper(this)

        setupView()
    }

    private fun setupView() {
        adapter = ItemAdapter(items)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        reloadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_add_name) {
            didClickPlus()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun didClickPlus() {
        val inputAlert = AlertDialog.Builder(this)
        inputAlert.setTitle("Add name")
        inputAlert.setMessage("Your name is: ")

        val userInput = EditText(this)
        inputAlert.setView(userInput)
        inputAlert.setPositiveButton("新增") { _, _ ->
            addNewName(userInput.text.toString())
        }
        inputAlert.setNegativeButton("取消") { _, _ ->
            Log.d(TAG, "didClickPlus: 取消按鈕被按下")
        }

        inputAlert.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun reloadData() {
        items = dbHelper.getNames()
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    private fun addNewName(name: String) {
        dbHelper.addName(name)
        reloadData()
    }
}