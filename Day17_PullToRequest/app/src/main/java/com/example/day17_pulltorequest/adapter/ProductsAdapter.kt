package com.example.day17_pulltorequest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.day17_pulltorequest.R
import com.example.day17_pulltorequest.model.ProductsModel

class ProductsAdapter(var products:ArrayList<ProductsModel>):RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bindInformation(product: ProductsModel) {
            itemView.findViewById<TextView>(R.id.nameTextView).text = product.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        val metrics = parent.context.resources.displayMetrics

        Log.e("log", "${metrics.widthPixels}")
        view.minimumWidth = 525 * (1080 / metrics.widthPixels)
        view.minimumHeight = 150

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindInformation(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}