package com.name.nick.generatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GirlsNameActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_girls_name) // your XML

        recyclerView = findViewById(R.id.recyclerView)

        val girlsNames = getMuslimGirlsNames()

        val adapter = GirlsNameAdapter(girlsNames) { selectedName ->
            // handled inside adapter
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getMuslimGirlsNames(): List<String> {
        return listOf(
            "Ayesha", "Fatima", "Zainab", "Maryam", "Hafsa", "Khadija",
            "Noor", "Sana", "Iqra", "Areeba", "Mehwish", "Laiba",
            "Mahnoor", "Hira", "Anaya", "Saba", "Rabia", "Aleena",
            "Eman", "Zoya", "Aliza", "Amna", "Nimra", "Kinza",
            "Sidra", "Fiza", "Aiman", "Komal", "Shanza", "Arisha",
            "Minal", "Nawal", "Samaira", "Zunaira", "Arooba",
            "Kainat", "Rida", "Tooba", "Wardah", "Sehrish",
            "Bushra", "Farah", "Hoorain", "Zimal", "Neha",
            "Anum", "Hania", "Momina", "Yusra", "Zehra"
        )
    }
}