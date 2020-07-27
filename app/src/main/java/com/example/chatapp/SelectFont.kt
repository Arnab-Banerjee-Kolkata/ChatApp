package com.example.chatapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectFont : AppCompatActivity(), FontAdapter.OnItemListener {

    lateinit var font: ArrayList<Int>
    lateinit var fontName: ArrayList<String>
    lateinit var fontList: RecyclerView
    lateinit var fontLayoutManager: RecyclerView.LayoutManager
    lateinit var fontAdapter: FontAdapter
    lateinit var myIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_font)

        fontList = findViewById(R.id.fontList) as RecyclerView

        font = ArrayList<Int>()
        fontName = ArrayList<String>()
        initializeFonts(font, fontName)

        fontLayoutManager = GridLayoutManager(this, 1)
        fontList.layoutManager = fontLayoutManager
        fontList.setHasFixedSize(true)
        fontAdapter = FontAdapter(font, fontName)
        fontList.adapter = fontAdapter
        fontAdapter.mClickListener = this

    }

    override fun onItemClick(pos: Int) {
        val selectedFont = font.get(pos)
        myIntent = Intent(applicationContext, MainActivity::class.java)
        myIntent.putExtra("selectedFont", selectedFont)
        setResult(Activity.RESULT_OK, myIntent)
        finish()
    }

    private fun initializeFonts(font: ArrayList<Int>, fontName: ArrayList<String>) {
        font.add(R.font.mr_dafoe_regular)
        font.add(R.font.josefin_sans_regular)
        font.add(R.font.montserrat_regular)
        font.add(R.font.leaguespartan_bold)
        font.add(R.font.vidaloka_regular)
        font.add(R.font.roboto)
        //font.add(R.font.apapa)
        font.add(R.font.sweet_sensations)
        font.add(R.font.rage_italic)
        font.add(R.font.all_over_again)
        font.add(R.font.alphabetized)
        font.add(R.font.alice)
        font.add(R.font.atlandsketches)
        font.add(R.font.giddyup)

        fontName.add("Mr dafoe regular")
        fontName.add("Josefin sans regular")
        fontName.add("Monteserrat regular")
        fontName.add("Leaguespartan bold")
        fontName.add("Vidaloka regular")
        fontName.add("Roboto")
        //fontName.add("A papa")
        fontName.add("Sweet sensations")
        fontName.add("Rage italic")
        fontName.add("All over again")
        fontName.add("Alphabetized")
        fontName.add("Alice")
        fontName.add("Atlandsketches")
        fontName.add("Giddyup")
    }
}
