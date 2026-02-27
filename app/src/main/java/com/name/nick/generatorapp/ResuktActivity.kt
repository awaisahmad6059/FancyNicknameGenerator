package com.name.nick.generatorapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ResuktActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resukt)

        val tvNickName = findViewById<android.widget.TextView>(R.id.tvNickName)
        val btnCopy = findViewById<MaterialButton>(R.id.btnCopy)
        val btnShare = findViewById<MaterialButton>(R.id.btnShare)
        val btnSave = findViewById<MaterialButton>(R.id.btnSave)

        val name = intent.getStringExtra("FINAL_NAME") ?: ""
        tvNickName.text = name

        // Copy
        btnCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("name", name)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        }

        // Share
        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, name)
            startActivity(Intent.createChooser(intent, "Share via"))
        }

        // Save → Only Toast + save in prefs
        btnSave.setOnClickListener {
            saveName(name)
            Toast.makeText(this, "Your design saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveName(name: String) {
        val prefs = getSharedPreferences("saved_names", MODE_PRIVATE)
        val set = prefs.getStringSet("names", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        set.add(name)
        prefs.edit().putStringSet("names", set).apply()
    }

    // Back press → MenuActivity
    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}