package com.name.nick.generatorapp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GirlsNameAdapter(
    private val items: List<String>,
    private val click: (String) -> Unit
) : RecyclerView.Adapter<GirlsNameAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val btnSelect: Button = view.findViewById(R.id.btnSelect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gamerz, parent, false) // reuse same item layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val name = items[position]
        holder.tvName.text = name

        holder.btnSelect.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, GenerateNameActivity::class.java)
            intent.putExtra("USER_NAME", name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}