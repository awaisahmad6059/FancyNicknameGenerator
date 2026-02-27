package com.name.nick.generatorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SavedAdapter(
    private val list: List<String>,
    private val onCopy: (String) -> Unit,
    private val onDelete: (String) -> Unit
) : RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvNickName)
        val ivCopy: ImageView = view.findViewById(R.id.ivCopy)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemsavename, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = list[position]
        holder.tvName.text = name

        holder.ivCopy.setOnClickListener { onCopy(name) }
        holder.ivDelete.setOnClickListener { onDelete(name) }
    }
}