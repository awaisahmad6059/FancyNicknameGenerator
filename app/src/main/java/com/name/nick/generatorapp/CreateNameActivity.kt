package com.name.nick.generatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class CreateNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_name)

        val etName = findViewById<EditText>(R.id.etNameInput)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        val cardgamerz = findViewById<CardView>(R.id.cardgamerz)
        cardgamerz.setOnClickListener {

            val intent = Intent(this, GamerzActivity::class.java)
            startActivity(intent)
        }
        val cardStylish = findViewById<CardView>(R.id.cardStylish)
        cardStylish.setOnClickListener {

            val intent = Intent(this, StylishNameActivity::class.java)
            startActivity(intent)
        }
        val cardAttitude = findViewById<CardView>(R.id.cardAttitude)
        cardAttitude.setOnClickListener {

            val intent = Intent(this, AttitudeNameActivity::class.java)
            startActivity(intent)
        }
        val cardGirls = findViewById<CardView>(R.id.cardGirls)
        cardGirls.setOnClickListener {

            val intent = Intent(this, GirlsNameActivity::class.java)
            startActivity(intent)
        }
        val cardBoys = findViewById<CardView>(R.id.cardBoys)
        cardBoys.setOnClickListener {

            val intent = Intent(this, BoysNameActivity::class.java)
            startActivity(intent)
        }

        btnGenerate.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                etName.error = "Enter your name"
                return@setOnClickListener
            }
            val intent = Intent(this, GenerateNameActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
    }
}