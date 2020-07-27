package com.example.chatapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SelectQuote : AppCompatActivity(), View.OnClickListener {
    lateinit var qList: ListView
    lateinit var arQuote: ArrayList<String>
    lateinit var qt: String
    var category = arrayOf("inspiration", "love", "sadness", "motivation", "determination", "poetry", "team", "sports")
    lateinit var insTv: TextView
    lateinit var loveTv: TextView
    lateinit var sadTv: TextView
    lateinit var motiTv: TextView
    lateinit var deterTv: TextView
    lateinit var poetTv: TextView
    lateinit var teamTv: TextView
    lateinit var spoTv: TextView
    lateinit var options: Array<TextView>
    lateinit var nextBtn: Button
    lateinit var backBtn: Button
    lateinit var btmBar: LinearLayout
    lateinit var myIntent: Intent
    val REQUEST_SELECTED_STYLE = 45

    //    companion object {
//        lateinit var mSelectedItem
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_quote)

        qList = findViewById(R.id.quoteList)
        insTv = findViewById(R.id.insTv) as TextView
        loveTv = findViewById(R.id.loveTv) as TextView
        sadTv = findViewById(R.id.sadTv) as TextView
        motiTv = findViewById(R.id.motiTv) as TextView
        deterTv = findViewById(R.id.deterTv) as TextView
        poetTv = findViewById(R.id.poetTv) as TextView
        teamTv = findViewById(R.id.teamTv) as TextView
        spoTv = findViewById(R.id.spoTv) as TextView
        btmBar = findViewById(R.id.btmBar) as LinearLayout
        nextBtn = findViewById(R.id.nextBtn) as Button
        backBtn = findViewById(R.id.backBtn) as Button

        backBtn.setOnClickListener(this)
        nextBtn.setOnClickListener(this)

        options = arrayOf(insTv, loveTv, sadTv, motiTv, deterTv, poetTv, teamTv, spoTv)
        btmBar.visibility = View.GONE

        for (a in 0 until options.size) {
            options[a].setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    arQuote = QuoteArray.getArrayList(category[a])
                    qList.adapter = QuoteAdapter(applicationContext, arQuote)
                    options[a].setBackgroundColor(applicationContext.resources.getColor(R.color.selectColor))
                    options[a].setTextColor(Color.WHITE)
                    for (b in 0 until options.size) {
                        if (b != a) {
                            options[b].setTextColor(Color.BLACK)
                        }
                    }
                }

            })

        }

        arQuote = QuoteArray.getArrayList(category[0])


        qList.adapter = QuoteAdapter(applicationContext, arQuote)
        qList.setOnItemClickListener()
        { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            btmBar.visibility = View.VISIBLE
            for (a in 0 until arQuote.size) {
                if (qList.getChildAt(a) != null) {
                    if (a != position)
                        qList.getChildAt(a).setBackgroundColor(Color.WHITE)
                    else
                        qList.getChildAt(a).setBackgroundColor(applicationContext.resources.getColor(R.color.deselectBgColor))
                }
            }
            qt = arQuote.get(position)
            myIntent = Intent(applicationContext, SelectStyle::class.java)
            myIntent.putExtra("selectedQuote", arQuote.get(position))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECTED_STYLE && resultCode == Activity.RESULT_OK) {
            val font = data?.getIntExtra("font", 1)
            var myIntent = Intent(applicationContext, SelectStyle::class.java)
            myIntent.putExtra("selectedQuote", qt)
            myIntent.putExtra("font", font)
            myIntent.putExtra("selectedBg", data?.getIntExtra("selectedBg", 1))
            myIntent.putExtra("selectedFontColor", data?.getIntExtra("selectedFontColor", 1))
            myIntent.putExtra("selectedRotation", data?.getFloatExtra("selectedRotation", 1f))
            setResult(Activity.RESULT_OK, myIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (btmBar.visibility == View.VISIBLE) {
            btmBar.visibility = View.GONE
            for (a in 0 until arQuote.size) {
                if (qList.getChildAt(a) != null)
                    qList.getChildAt(a).setBackgroundColor(Color.WHITE)
            }
        } else
            super.onBackPressed()
    }

    override fun onClick(v: View?) {
        if (v!!.equals(nextBtn)) {
            startActivityForResult(myIntent, REQUEST_SELECTED_STYLE)
        } else if (v!!.equals(backBtn)) {
            onBackPressed()
        }
    }
}
