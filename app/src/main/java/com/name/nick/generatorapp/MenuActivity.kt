package com.name.nick.generatorapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val createNameCard = findViewById<CardView>(R.id.cretename)
        createNameCard.setOnClickListener {
            startActivity(Intent(this, CreateNameActivity::class.java))
        }

        val savename = findViewById<CardView>(R.id.savename)
        savename.setOnClickListener {
            startActivity(Intent(this, SavedActivity::class.java))
        }
        val randomname = findViewById<CardView>(R.id.randomname)
        randomname.setOnClickListener {
            startActivity(Intent(this, RandomActivity::class.java))
        }
    }

    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}