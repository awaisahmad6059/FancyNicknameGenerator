package com.name.nick.generatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class GenerateNameActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var styleAdapter: NameStyleAdapter

    private val styleSets = mapOf(
        "stars" to listOf('★', '☆', '✰', '✩', '✭', '✮', '✯', '✡', '✦', '✧', '✪', '✫', '✬', '✭', '✮', '✯', '✰', '✱', '✲', '✳', '✴', '✵', '✶', '✷', '✸', '✹', '✺', '✻', '✼', '✽', '✾', '✿', '❀', '❁', '❂', '❃', '❄', '❅', '❆', '❇', '❈', '❉', '❊', '❋'),
        "brackets" to listOf('[', ']', '(', ')', '{', '}', '〈', '〉', '⟨', '⟩', '〈', '〉', '❮', '❯', '❬', '❭', '❰', '❱', '❪', '❫', '❴', '❵', '❲', '❳', '⦃', '⦄', '⦅', '⦆', '⦇', '⦈', '⦉', '⦊', '⦋', '⦌', '⦍', '⦎', '⦏', '⦐', '⦑', '⦒', '⦓', '⦔', '⦕', '⦖', '⦗', '⦘', '⧘', '⧙', '⧚', '⧛', '⧼', '⧽', '⸂', '⸃', '⸄', '⸅', '⸉', '⸊', '⸌', '⸍', '⸜', '⸝', '⸠', '⸡', '⸢', '⸣', '⸤', '⸥', '⸦', '⸧', '⸨', '⸩', '〔', '〕', '〖', '〗', '〘', '〙', '〚', '〛', '《', '》', '「', '」', '『', '』', '〔', '〕', '〖', '〗', '〘', '〙', '〚', '〛', '〝', '〞', '〟', '〰', '〽', '〾', '〿', '⌈', '⌉', '⌊', '⌋', '〈', '〉', '❨', '❩', '❪', '❫', '❬', '❭', '❮', '❯', '❰', '❱'),
        "symbols" to listOf('♡', '♢', '♤', '♧', '♚', '♛', '♜', '♝', '♞', '♟', '♔', '♕', '♖', '♗', '♘', '♙', '♩', '♪', '♫', '♬', '♭', '♮', '♯', '♦', '♨', '♣', '♢', '♠', '♡', '♦', '♧', '♥', '♤', '♠', '♚', '♛', '♜', '♝', '♞', '♟', '♔', '♕', '♖', '♗', '♘', '♙'),
        "circles" to listOf('○', '●', '◌', '◍', '◎', '◯', '⭕', '⭖', '⭗', '⭘', '⭙', '⭚', '⭛', '⭜', '⭝', '⭞', '⭟', '⭠', '⭡', '⭢', '⭣', '⭤', '⭥', '⭦', '⭧', '⭨', '⭩', '⭪', '⭫', '⭬', '⭭', '⭮', '⭯', '⭰', '⭱', '⭲', '⭳', '⭴', '⭵', '⭶', '⭷', '⭸', '⭹', '⭺', '⭻', '⭼', '⭽', '⭾', '⭿'),
        "arrows" to listOf('↑', '↓', '←', '→', '↖', '↗', '↘', '↙', '↕', '↔', '⇐', '⇒', '⇑', '⇓', '⇔', '⇗', '⇘', '⇙', '⇚', '⇛', '⇜', '⇝', '⇞', '⇟', '⇠', '⇡', '⇢', '⇣', '⇤', '⇥', '⇦', '⇧', '⇨', '⇩', '⇪', '⇫', '⇬', '⇭', '⇮', '⇯', '⇰', '⇱', '⇲', '⇳', '⇴', '⇵', '⇶', '⇷', '⇸', '⇹', '⇺', '⇻', '⇼', '⇽', '⇾', '⇿'),
    )

    private lateinit var styleList: List<String>
    private lateinit var symbolList: List<String>

    private lateinit var originalName: String
    private var selectedLeftSymbol: String? = null
    private var selectedRightSymbol: String? = null
    private var selectedStyle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_name)

        tvName = findViewById(R.id.tvName)
        recyclerView = findViewById(R.id.recyclerNames)
        tabLayout = findViewById(R.id.tabLayout)

        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            val finalName = tvName.text.toString()

            val intent = Intent(this, ResuktActivity::class.java)
            intent.putExtra("FINAL_NAME", finalName)
            startActivity(intent)
        }
        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        tvName.text = userName
        originalName = intent.getStringExtra("USER_NAME") ?: "User"
        tvName.text = originalName

        styleList = generateStyles(originalName)
        symbolList = styleSets.values.flatten().map { it.toString() }

        tabLayout.getTabAt(1)?.select()
        setupRecyclerView(styleList, 2)
        val btnAutoGenerate = findViewById<Button>(R.id.btnAutoGenerate)
        btnAutoGenerate.setOnClickListener {
            // Randomly pick a styled version from styleList
            selectedStyle = styleList.random()

            // Random left and right symbols
            selectedLeftSymbol = symbolList.random()
            selectedRightSymbol = symbolList.random()

            // Update the TextView
            updateNameText()
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.text) {
                    "Left" -> setupRecyclerView(symbolList, 6)
                    "Style" -> setupRecyclerView(styleList, 1)
                    "Right" -> setupRecyclerView(symbolList, 6)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupRecyclerView(list: List<String>, spanCount: Int) {
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        styleAdapter = NameStyleAdapter(list) { item ->
            when (tabLayout.selectedTabPosition) {
                0 -> { // Left symbol
                    selectedLeftSymbol = item
                }
                1 -> { // Style
                    selectedStyle = item
                }
                2 -> { // Right symbol
                    selectedRightSymbol = item
                }
            }
            updateNameText()
        }
        recyclerView.adapter = styleAdapter
    }

    private fun updateNameText() {
        var text = selectedStyle ?: originalName

        selectedLeftSymbol?.let {
            text = "$it $text"
        }

        selectedRightSymbol?.let {
            text = "$text $it"
        }

        tvName.text = text
    }

    private fun generateStyles(name: String): List<String> {
        val styledList = mutableListOf<String>()
        repeat(50) {
            val styled = name.map { randomCharStyle(it) }.joinToString("")
            styledList.add(styled)
        }
        return styledList
    }

    private fun randomCharStyle(char: Char): String {
        val map = mapOf(
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
            'q' to listOf("q","𝓺","𝒬"),
            'r' to listOf("ʀ","𝓻","𝓡"),
            's' to listOf("ѕ","𝓼","𝒮"),
            't' to listOf("т","𝓽","𝒯"),
            'u' to listOf("υ","𝓾","𝒰"),
            'v' to listOf("ν","𝓿","𝒱"),
            'w' to listOf("ω","𝔀","𝒲"),
            'x' to listOf("χ","𝔁","𝒳"),
            'y' to listOf("у","𝔂","𝒴"),
            'z' to listOf("z","𝔃","𝒵")
        )
        val lower = char.lowercaseChar()
        return map[lower]?.random() ?: char.toString()
    }
}