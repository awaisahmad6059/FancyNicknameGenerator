package com.name.nick.generatorapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class RandomActivity : AppCompatActivity() {

    private lateinit var tvNickName: TextView

    private val styleSets = mapOf(
        "stars" to listOf('★', '☆', '✰', '✩', '✭', '✮', '✯', '✡', '✦', '✧', '✪', '✫', '✬', '✱', '✲', '✳', '✴', '✵', '✶', '✷', '✸', '✹', '✺', '✻', '✼', '✽', '✾', '✿', '❀', '❁', '❂', '❃', '❄', '❅', '❆', '❇', '❈', '❉', '❊', '❋'),
        "brackets" to listOf('[', ']', '(', ')', '{', '}', '〈', '〉', '⟨', '⟩', '〈', '〉', '❮', '❯', '❬', '❭', '❰', '❱', '❪', '❫', '❴', '❵', '❲', '❳'),
        "symbols" to listOf('♡', '♢', '♤', '♧', '♚', '♛', '♜', '♝', '♞', '♟', '♔', '♕', '♖', '♗', '♘', '♙', '♪', '♫', '♬', '♦', '♣', '♠', '♥'),
        "circles" to listOf('○', '●', '◌', '◍', '◎', '◯', '⭕'),
        "arrows" to listOf('↑', '↓', '←', '→', '↖', '↗', '↘', '↙', '↔', '⇐', '⇒', '⇑', '⇓')
    )

    // Flatten all symbols into one list
    private val allSymbols = styleSets.values.flatten().map { it.toString() }

    private val styles = mapOf(
        'a' to listOf("α","𝓪","𝒶"),
        'b' to listOf("β","𝓫","𝒷"),
        'c' to listOf("ç","𝓬","𝒸"),
        'd' to listOf("δ","𝓭","𝒹"),
        'e' to listOf("ε","𝓮","ℯ"),
        'f' to listOf("ƒ","𝓯","𝒻"),
        'g' to listOf("ɢ","𝓰","𝓖"),
        'h' to listOf("н","𝓱","𝒽"),
        'i' to listOf("ɪ","𝓲","𝒾"),
        'j' to listOf("ʝ","𝓳","𝒿"),
        'k' to listOf("к","𝓴","𝓚"),
        'l' to listOf("ℓ","𝓵","𝓛"),
        'm' to listOf("м","𝓶","𝓜"),
        'n' to listOf("ɴ","𝓷","𝓝"),
        'o' to listOf("σ","𝓸","𝒪"),
        'p' to listOf("ρ","𝓹","𝒫"),
        'r' to listOf("ʀ","𝓻","𝓡"),
        's' to listOf("ѕ","𝓼","𝒮"),
        't' to listOf("т","𝓽","𝒯"),
        'u' to listOf("υ","𝓾","𝒰"),
        'v' to listOf("ν","𝓿","𝒱"),
        'w' to listOf("ω","𝔀","𝒲"),
        'y' to listOf("у","𝔂","𝒴")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        tvNickName = findViewById(R.id.tvNickName)

        val name = intent.getStringExtra("FINAL_NAME") ?: ""
        tvNickName.text = name

        val btnRandom = findViewById<MaterialButton>(R.id.Random)
        val btnCopy = findViewById<MaterialButton>(R.id.btnCopy)
        val btnShare = findViewById<MaterialButton>(R.id.btnShare)
        val btnSave = findViewById<MaterialButton>(R.id.btnSave)

        btnCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("name", tvNickName.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        }

        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, tvNickName.text.toString())
            startActivity(Intent.createChooser(intent, "Share via"))
        }

        btnSave.setOnClickListener {
            val currentName = tvNickName.text.toString()
            saveName(currentName)
            Toast.makeText(this, "Your design saved", Toast.LENGTH_SHORT).show()
        }
        generateRandomName()

        btnRandom.setOnClickListener {
            generateRandomName()
        }
    }

    private fun generateRandomName() {
        val baseNames = listOf(
            "Bad Boy", "King Khan", "Attitude Prince", "Silent Killer",
            "Alone Hero", "Danger Boy", "Royal Nawab", "Mr Perfect",
            "Devil Mind", "No Mercy", "Born Legend", "Rule Breaker",
            "Heart Hacker", "Power King", "Dark Prince", "Royal Blood",
            "Boss Mind", "Fearless Soul", "Evil Smile", "One Man Army",
            "Nawab Style", "King Of Hearts", "Attitude King", "Game Changer",
            "Royal Entry", "Unique Boy", "Legend Boy", "Dangerous Mind",
            "Mr Attitude", "Hero Mind", "Royal King", "Boss Attitude",
            "Badmash Boy", "King Is Back", "Royal Hero", "Mind Blower",
            "Attitude Master", "Silent King", "Alone King", "Royal Devil",
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
        val name = baseNames.random()

        val styled = name.map {
            styles[it.lowercaseChar()]?.random() ?: it.toString()
        }.joinToString("")

        val left = allSymbols.random()
        val right = allSymbols.random()

        val finalName = "$left $styled $right"
        tvNickName.text = finalName
    }
    private fun saveName(name: String) {
        val prefs = getSharedPreferences("saved_names", MODE_PRIVATE)
        val set = prefs.getStringSet("names", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        set.add(name)
        prefs.edit().putStringSet("names", set).apply()
    }

    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}