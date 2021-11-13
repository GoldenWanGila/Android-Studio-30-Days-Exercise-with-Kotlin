package com.example.day12_githubstar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.day12_githubstar.databinding.ActivityMainBinding
import com.example.day12_githubstar.model.ProjectModel
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.searchButton.setOnClickListener(searchButtonHandler)
    }

    private val searchButtonHandler = View.OnClickListener {
        setupOkHttpClient()
        hideSoftInput(binding.editView)
    }

    private fun hideSoftInput(editText: EditText) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    private fun setupOkHttpClient() {

        val username = binding.editView.text
        if (username.isEmpty()) {
            Toast.makeText(this, "please input username", Toast.LENGTH_SHORT).show()
            return
        }

        val client = OkHttpClient()
        val request =
            Request.Builder().url("https://api.github.com/users/$username/starred").build()
        // Schedules the request to be executed at some point in the future.
        client.newCall(request).enqueue(object : Callback {
            // Called when the request could not be executed due to cancellation.
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@MainActivity, "get data failed", Toast.LENGTH_SHORT).show()
            }

            // Called when the HTTP response was successfully returned by the remote server.
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body()?.string()
                val json = JSONArray(responseData)
                val projects: ArrayList<ProjectModel> = ArrayList()

                if (json.length() == 0)
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@MainActivity, "Username doesn't exit/user doesn't star any project", Toast.LENGTH_SHORT).show()
                    }
                else {
                    for (position in 0 until json.length()) {
                        val project = setupProjectModel(json, position)
                        projects.add(project)
                    }
                    // Intent setup
                    val intent = Intent(this@MainActivity, ProjectListActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("projects", projects)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        })
    }

    private fun setupProjectModel(json: JSONArray, position: Int): ProjectModel {
        // 從 api json 檔獲取資料，若有多個按星星的 github project，position 可以分別獲取各個不同的 github project
        val item = json.getJSONObject(position)
        val owner = item.getJSONObject("owner")
        // 設定 projectModel 的相關參數
        val ownerName = owner.get("login").toString()
        val avatarURL = owner.get("avatar_url").toString()
        val projectName = item.get("name").toString()
        val description = item.get("description").toString()
        val starCount = item.get("stargazers_count").toString().toInt()
        val forkCount = item.get("forks_count").toString().toInt()

        return ProjectModel(
            projectName,
            description,
            avatarURL,
            starCount,
            forkCount,
            ownerName
        )
    }
}