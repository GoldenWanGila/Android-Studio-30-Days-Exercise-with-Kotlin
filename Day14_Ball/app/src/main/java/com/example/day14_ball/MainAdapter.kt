package com.example.day14_ball

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val clickListener:(position:Int) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val items : ArrayList<String> = arrayListOf("Value Animator", "Object Animator", "Animator Set", "Interpolator")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindInformation(position: Int, description: String) {
            itemView.findViewById<TextView>(R.id.optionNumber).text = "$position"
            itemView.findViewById<TextView>(R.id.optionDescription).text = description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{ clickListener(position) }
        holder.bindInformation(position, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}