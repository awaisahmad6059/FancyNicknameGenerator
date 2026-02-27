package com.name.nick.generatorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BoysNameActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boys_name) // Use same XML as GamerzActivity

        recyclerView = findViewById(R.id.recyclerView)

        val boysNames = getMuslimBoysNames()

        val adapter = BoysNameAdapter(boysNames) { selectedName ->
            // Open GenerateNameActivity with selected name
            val intent = Intent(this, GenerateNameActivity::class.java)
            intent.putExtra("USER_NAME", selectedName)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // List of Muslim boy names
    private fun getMuslimBoysNames(): List<String> {
        return listOf(
            "Ahmed", "Ali", "Hassan", "Hussain", "Omar", "Usman", "Bilal",
            "Zain", "Yusuf", "Ibrahim", "Ismail", "Hamza", "Tariq", "Ayaan",
            "Imran", "Sami", "Farhan", "Khalid", "Abdullah", "Rashid",
            "Ammar", "Nabeel", "Saad", "Fahad", "Adnan", "Shahbaz", "Yahya",
            "Azlan", "Junaid", "Rayan", "Zubair", "Haroon", "Salman", "Talha",
            "Waleed", "Nouman", "Murtaza", "Faisal", "Arslan", "Sulaiman",
            "Hamid", "Khurram", "Zainul", "Yasir", "Adeel", "Haris", "Aariz", "Raheel", "Shahroz", "Imad"
        )
    }
}