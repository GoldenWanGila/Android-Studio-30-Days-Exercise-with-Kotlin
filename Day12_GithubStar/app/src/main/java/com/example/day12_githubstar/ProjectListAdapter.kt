package com.example.day12_githubstar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.day12_githubstar.model.ProjectModel

class ProjectListAdapter(private val projects: ArrayList<ProjectModel>) :
    RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindProjectModel(model: ProjectModel) {
            with(model) {
                itemView.findViewById<TextView>(R.id.projectTextView).text = projectName
                itemView.findViewById<TextView>(R.id.descriptionTextView).text = description
                itemView.findViewById<TextView>(R.id.starTextView).text = "$starCount"
                itemView.findViewById<TextView>(R.id.forkTextView).text = "$forkCount"
                itemView.findViewById<TextView>(R.id.usernameTextView).text = username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_star_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProjectModel(projects[position])
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}