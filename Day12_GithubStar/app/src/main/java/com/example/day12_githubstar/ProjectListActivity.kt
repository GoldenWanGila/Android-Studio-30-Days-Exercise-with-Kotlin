package com.example.day12_githubstar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day12_githubstar.databinding.ActivityProjectListBinding
import com.example.day12_githubstar.model.ProjectModel

class ProjectListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.projectListRecyclerView.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val projects: ArrayList<ProjectModel> =
            intent.extras?.getParcelableArrayList<ProjectModel>("projects") as ArrayList<ProjectModel>
        val adapter = ProjectListAdapter(projects)
        binding.projectListRecyclerView.adapter = adapter
    }
    // 設定當右上角返回鍵備按下時，要做的事件 -> 返回上一頁
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