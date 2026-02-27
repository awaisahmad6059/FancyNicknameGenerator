package com.name.nick.generatorapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedActivity : AppCompatActivity() {

    private lateinit var adapter: SavedAdapter
    private lateinit var list: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        val recycler = findViewById<RecyclerView>(R.id.rvNickNames)
        recycler.layoutManager = LinearLayoutManager(this)

        list = loadNames().toMutableList()
        adapter = SavedAdapter(list,
            onCopy = { copyText(it) },
            onDelete = { deleteName(it) })

        recycler.adapter = adapter
    }

    private fun loadNames(): Set<String> {
        val prefs = getSharedPreferences("saved_names", MODE_PRIVATE)
        return prefs.getStringSet("names", emptySet()) ?: emptySet()
    }

    private fun saveAll() {
        val prefs = getSharedPreferences("saved_names", MODE_PRIVATE)
        prefs.edit().putStringSet("names", list.toSet()).apply()
    }

    private fun deleteName(name: String) {
        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this nickname?")
            .setPositiveButton("Yes") { dialog, _ ->
                list.remove(name)
                saveAll()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun copyText(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("name", text))
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
    }

}