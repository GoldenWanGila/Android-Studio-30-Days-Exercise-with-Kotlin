package com.example.day21_layoutswitch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.day21_layoutswitch.R
import com.example.day21_layoutswitch.model.ItemModel

class LinearLayoutItemAdapter(private val items: List<ItemModel>) :
    RecyclerView.Adapter<LinearLayoutItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_big_image_view)
        val nameTextView: TextView = itemView.findViewById(R.id.item_big_name_text_view)
        val likeTextView: TextView = itemView.findViewById(R.id.item_big_like_text_view)
        val commentTextView: TextView = itemView.findViewById(R.id.item_big_comment_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_big, parent, false)
        return ItemViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageId)
        holder.nameTextView.text = item.name
        holder.likeTextView.text = "${item.likeCount} likes"
        holder.commentTextView.text = "${item.commentCount} comments"
    }

    override fun getItemCount(): Int {
        return items.size
    }

}