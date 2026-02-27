package com.name.nick.generatorapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GamerzAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<GamerzAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val btnSelect: Button = view.findViewById(R.id.btnSelect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gamerz, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = items[position]
        holder.tvName.text = name

        // Optional: highlight selected item when clicked
        holder.tvName.setBackgroundColor(0xFFFFFFFF.toInt()) // white by default

        // Handle Select button click
        holder.btnSelect.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, GenerateNameActivity::class.java)
            intent.putExtra("USER_NAME", name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}