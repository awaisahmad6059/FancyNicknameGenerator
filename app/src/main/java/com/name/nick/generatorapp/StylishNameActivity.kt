package com.name.nick.generatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.name.nick.generatorapp.StylishNameAdapter

class StylishNameActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stylish_name)

        recyclerView = findViewById(R.id.recyclerView)

        val names = getStylishNames()

        val adapter = StylishNameAdapter(names) { }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getStylishNames(): List<String> {
        return listOf(
            "★彡Awais彡★", "꧁༒Ali༒꧂", "『Zain』", "彡Hassan彡",
            "乂Hamza乂", "么Bilal么", "⚡Sami⚡", "ツFaizanツ",
            "★Imran★", "彡Saad彡", "꧁Ahmad꧂", "『Usman』",
            "メYasirメ", "★Fahad★", "彡Ayaan彡", "么Rayan么",
            "乂Arslan乂", "★Shahroz★", "ツZubairツ", "彡Haroon彡",
            "⚡Nouman⚡", "꧁Tariq꧂", "★Khalid★", "『Adnan』",
            "メTalhaメ", "★Hamid★", "彡Waleed彡", "么Sulaiman么",
            "乂Junaid乂", "ツAdeelツ", "★Haris★", "⚡Saif⚡",
            "彡Rashid彡", "『Murtaza』", "꧁Faisal꧂", "★Imad★",
            "メRaheelメ", "★Azlan★", "彡Yahya彡", "么Shayan么"
        )
    }
}