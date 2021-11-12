package com.example.day6_imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataList: MutableList<ImageModel>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    // 連結到layout的物件
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memo = view.findViewById<TextView>(R.id.memo)!!
        val imageView = view.findViewById<ImageView>(R.id.imageView)!!
    }

    // 用來處理每一個item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    // 回傳item list的長度，告訴adapter有幾個項目
    override fun getItemCount(): Int {
        return dataList.size
    }

    // 將「值」(這裡就是指memo和imageId)對應給介面
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(dataList[position].imageId)
        holder.memo.text = dataList[position].memo
    }
}