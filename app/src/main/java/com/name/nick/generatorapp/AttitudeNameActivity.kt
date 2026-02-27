package com.name.nick.generatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AttitudeNameActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attitude_name)

        recyclerView = findViewById(R.id.recyclerView)

        val names = getAttitudeNames()

        val adapter = AttitudeNameAdapter(names) { }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getAttitudeNames(): List<String> {
        return listOf(
            "Bad Boy", "King Khan", "Attitude Prince", "Silent Killer",
            "Alone Hero", "Danger Boy", "Royal Nawab", "Mr Perfect",
            "Devil Mind", "No Mercy", "Born Legend", "Rule Breaker",
            "Heart Hacker", "Power King", "Dark Prince", "Royal Blood",
            "Boss Mind", "Fearless Soul", "Evil Smile", "One Man Army",
            "Nawab Style", "King Of Hearts", "Attitude King", "Game Changer",
            "Royal Entry", "Unique Boy", "Legend Boy", "Dangerous Mind",
            "Mr Attitude", "Hero Mind", "Royal King", "Boss Attitude",
            "Badmash Boy", "King Is Back", "Royal Hero", "Mind Blower",
            "Attitude Master", "Silent King", "Alone King", "Royal Devil"
        )
    }
}