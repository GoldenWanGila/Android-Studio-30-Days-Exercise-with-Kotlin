package com.example.day6_imagelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 創造用於儲存要顯示的item的data class
data class ImageModel(val imageId: Int, val memo: String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(getDataList())
        recyclerView.adapter = adapter
    }

    // 回傳以ImageModel data class儲存的list
    private fun getDataList(): MutableList<ImageModel> {
        val imageList = arrayListOf<ImageModel>()
        for (i in 0..5) {
            val imageName = "image0${i + 1}"
            imageList.add(
                ImageModel(
                    findResourceIdByString(imageName),
                    imageName
                )
            )
        }
        return imageList
    }

    // 透過imageName來獲取image的id
    private fun findResourceIdByString(string: String): Int {
        return this.resources.getIdentifier(string, "drawable", this.packageName)
    }
}