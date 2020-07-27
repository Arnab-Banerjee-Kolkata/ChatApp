package com.example.chatapp

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class StyleAdapter(val demoText: ArrayList<String>, val font: ArrayList<Int>, val bg: ArrayList<Int>, val fontColor: ArrayList<Int>,
                   val rotationAngle: ArrayList<Float>) : RecyclerView.Adapter<StyleAdapter.ViewHolder>() {
    lateinit var myContext: Context
    var mClickListener: OnItemListener? = null
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): StyleAdapter.ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.style_cell, p0, false)
        myContext = p0?.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return demoText.size
    }

    override fun onBindViewHolder(p0: StyleAdapter.ViewHolder, position: Int) {
        p0?.styleSampleText.text = demoText.get(position).toString()
        p0?.styleSampleText.setTextColor(myContext.resources.getColor(fontColor.get(position)))
        p0?.styleSampleText.rotation = rotationAngle.get(position)
        if (demoText.get(position).length < 60)
            p0.styleSampleText.textSize = 30f / (demoText.get(position).length / 30f)
        else
            p0.styleSampleText.textSize = 30f / (demoText.get(position).length / 50f)
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, font.get(position))
        p0?.styleSampleText.setTypeface(typeFace, Typeface.ITALIC)
        p0.itemView.setOnClickListener {
            if (mClickListener != null)
                mClickListener!!.onItemClick(position)
        }

        p0?.sampleRel.setBackgroundResource(bg.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val styleSampleText = itemView.findViewById(R.id.styleSampleText) as TextView
        val sampleRel = itemView.findViewById(R.id.sampleRel) as RelativeLayout
    }

    interface OnItemListener {
        fun onItemClick(pos: Int)
    }

}