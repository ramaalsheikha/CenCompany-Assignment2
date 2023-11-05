package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domine.ItemsViewModel

class RecyclerViewAdapter(private val list: List<ItemsViewModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title :TextView = itemView.findViewById(R.id.tv_subtitle)
        val content :TextView = itemView.findViewById(R.id.tv_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_design, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel:ItemsViewModel = list[position]
        holder.title.text = itemViewModel.title
        holder.content.text = itemViewModel.content

    }


}