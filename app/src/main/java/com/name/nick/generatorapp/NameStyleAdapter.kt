package com.name.nick.generatorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameStyleAdapter(
    private val items: List<String>,
    private val click: (String) -> Unit
) : RecyclerView.Adapter<NameStyleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_name_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text = items[position]
        holder.tvItem.setOnClickListener {
            click(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}