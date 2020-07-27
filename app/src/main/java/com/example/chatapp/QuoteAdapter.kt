package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class QuoteAdapter(var context: Context, var quote: ArrayList<String>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var quoteText: TextView

        init {
            this.quoteText = row?.findViewById(R.id.quoteText) as TextView
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.quote_row, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var quote: String = getItem(position) as String
        viewHolder.quoteText.text = quote

        return view as View
    }

    override fun getItem(position: Int): Any {
        return quote.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return quote.count()
    }
}