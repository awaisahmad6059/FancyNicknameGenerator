package com.name.nick.generatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class GamerzActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamerz)

        recyclerView = findViewById(R.id.recyclerView)


        val gamerNames = generateGamerNames(50)

        val adapter = GamerzAdapter(gamerNames) // just pass the list, no lambda
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }

    private fun generateGamerNames(count: Int): List<String> {
        val prefixes = listOf("xX", "The", "Mr", "Dr", "🔥", "⚡", "Shadow", "Dark")
        val suffixes = listOf("Gamer", "Slayer", "Pro", "Master", "Wolf", "King", "Legend", "X")
        val names = mutableListOf<String>()

        repeat(count) {
            val prefix = prefixes.random()
            val name = suffixes.random()
            val number = Random.nextInt(0, 999)
            names.add("$prefix$name$number")
        }
        return names
    }
}