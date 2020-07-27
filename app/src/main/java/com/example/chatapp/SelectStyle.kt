package com.example.chatapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectStyle : AppCompatActivity(), StyleAdapter.OnItemListener, View.OnClickListener {


    lateinit var styleGrid: RecyclerView
    lateinit var demoText: ArrayList<String>
    lateinit var font: ArrayList<Int>
    lateinit var bg: ArrayList<Int>
    lateinit var fontColor: ArrayList<Int>
    lateinit var rotationAngle: ArrayList<Float>
    lateinit var styleLayoutManager: RecyclerView.LayoutManager
    private lateinit var stAdapter: StyleAdapter
    lateinit var nextBtn: Button
    lateinit var backBtn: Button
    lateinit var btmBar: LinearLayout
    lateinit var myIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_style)

        styleGrid = findViewById(R.id.styleGrid) as RecyclerView
        btmBar = findViewById(R.id.btmBar) as LinearLayout
        nextBtn = findViewById(R.id.nextBtn) as Button
        backBtn = findViewById(R.id.backBtn) as Button

        demoText = ArrayList<String>()
        font = ArrayList<Int>()
        bg = ArrayList<Int>()
        fontColor = ArrayList<Int>()
        rotationAngle = ArrayList<Float>()

        initializeArrayLists()

        styleLayoutManager = GridLayoutManager(this, 2)
        styleGrid.layoutManager = styleLayoutManager
        styleGrid.setHasFixedSize(true)
        stAdapter = StyleAdapter(demoText, font, bg, fontColor, rotationAngle)
        styleGrid.adapter = stAdapter
        stAdapter.mClickListener = this

        nextBtn.setOnClickListener(this)
        backBtn.setOnClickListener(this)
        btmBar.visibility = View.GONE

    }

    override fun onBackPressed() {
        if (btmBar.visibility == View.VISIBLE) {
            btmBar.visibility = View.GONE
            for (a in 0 until bg.size) {
                var view: View? = styleLayoutManager.findViewByPosition(a)
                if (view != null) {
                    view!!.setBackgroundColor(Color.WHITE)
                }
            }
        } else
            super.onBackPressed()
    }

    override fun onItemClick(pos: Int) {
        val font = font.get(pos)
        myIntent = Intent(applicationContext, SelectQuote::class.java)
        myIntent.putExtra("font", font)
        myIntent.putExtra("checking", "hello")
        myIntent.putExtra("selectedBg", bg.get(pos))
        myIntent.putExtra("selectedFontColor", fontColor.get(pos))
        myIntent.putExtra("selectedRotation", rotationAngle.get(pos))
        for (a in 0 until bg.size) {
            var view: View? = styleLayoutManager.findViewByPosition(a)
            if (view != null) {
                if (a != pos) {
                    view!!.setBackgroundColor(Color.WHITE)
                } else {
                    view!!.setBackgroundColor(applicationContext.resources.getColor(R.color.deselectBgColor))
                }
            }
        }
        btmBar.visibility = View.VISIBLE

    }

    fun initializeArrayLists() {
        var myStr = intent.getStringExtra("selectedQuote").toString()

        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)
        demoText.add(myStr)

        font.add(R.font.mr_dafoe_regular)
        font.add(R.font.josefin_sans_regular)
        font.add(R.font.josefin_sans_regular)
        font.add(R.font.montserrat_regular)
        font.add(R.font.leaguespartan_bold)
        font.add(R.font.vidaloka_regular)
        font.add(R.font.mr_dafoe_regular)
        font.add(R.font.josefin_sans_regular)
        font.add(R.font.montserrat_regular)
        font.add(R.font.montserrat_regular)
        font.add(R.font.leaguespartan_bold)
        font.add(R.font.vidaloka_regular)

        bg.add(R.drawable.quote_bg)
        bg.add(R.drawable.quote_bg2)
        bg.add(R.drawable.quote_bg3)
        bg.add(R.drawable.quote_bg4)
        bg.add(R.drawable.quote_bg5)
        bg.add(R.drawable.quote_bg6)
        bg.add(R.drawable.quote_bg)
        bg.add(R.drawable.quote_bg2)
        bg.add(R.drawable.quote_bg3)
        bg.add(R.drawable.quote_bg4)
        bg.add(R.drawable.quote_bg5)
        bg.add(R.drawable.quote_bg6)

        fontColor.add(R.color.color1)
        fontColor.add(R.color.color2)
        fontColor.add(R.color.color3)
        fontColor.add(R.color.color4)
        fontColor.add(R.color.color5)
        fontColor.add(R.color.color6)
        fontColor.add(R.color.color1)
        fontColor.add(R.color.color2)
        fontColor.add(R.color.color3)
        fontColor.add(R.color.color4)
        fontColor.add(R.color.color5)
        fontColor.add(R.color.color6)

        rotationAngle.add(-10F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(-10F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)
        rotationAngle.add(0F)


    }

    override fun onClick(v: View?) {
        if (v!!.equals(nextBtn)) {
            setResult(Activity.RESULT_OK, myIntent)
            finish()
        } else if (v!!.equals(backBtn)) {
            onBackPressed()
        }
    }

}


