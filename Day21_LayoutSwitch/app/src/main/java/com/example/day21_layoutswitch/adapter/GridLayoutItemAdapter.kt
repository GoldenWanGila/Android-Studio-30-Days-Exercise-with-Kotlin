package com.example.day21_layoutswitch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.day21_layoutswitch.R
import com.example.day21_layoutswitch.model.ItemModel

class GridLayoutItemAdapter(private val items: List<ItemModel>) :
    RecyclerView.Adapter<GridLayoutItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_small_image_view)
        val nameTextView: TextView = itemView.findViewById(R.id.item_small_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_small, parent, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageId)
        holder.nameTextView.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

}