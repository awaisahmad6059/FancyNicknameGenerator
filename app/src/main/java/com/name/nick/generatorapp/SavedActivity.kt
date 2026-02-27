package com.name.nick.generatorapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            onShare = { shareText(it) },
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
        list.remove(name)
        saveAll()
        adapter.notifyDataSetChanged()
    }

    private fun copyText(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("name", text))
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Share via"))
    }
}