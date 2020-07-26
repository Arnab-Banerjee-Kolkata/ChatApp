package com.example.chatapp

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.font_layout.view.*

class FontAdapter (val font: ArrayList<Int>, val fontName: ArrayList<String>): RecyclerView.Adapter<FontAdapter.ViewHolder>()
{
    lateinit var myContext: Context
    var mClickListener : OnItemListener?=null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FontAdapter.ViewHolder
    {
        myContext=p0?.context
        val v = LayoutInflater.from(myContext).inflate(R.layout.font_layout,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int
    {
        return font.size
    }

    override fun onBindViewHolder(p0: FontAdapter.ViewHolder, position: Int)
    {
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, font.get(position))
        p0?.sampleTxt.typeface=typeFace
        p0.sampleTxt.text=fontName[position]
        p0.itemView.fontApply.setOnClickListener {
            if(mClickListener!=null)
                mClickListener!!.onItemClick(position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val sampleTxt=itemView.findViewById(R.id.sampleTxt) as TextView
        val fontApply=itemView.findViewById(R.id.fontApply) as Button
    }

    interface OnItemListener {
        fun onItemClick(pos:Int)
    }

}