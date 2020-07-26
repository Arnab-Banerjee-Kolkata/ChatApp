package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class EmojiAdapter(var emojiName: ArrayList<Int>) : RecyclerView.Adapter<ViewHolder>() {
    var context: Context? = null
    private var mClickListener: OnEmojiListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.emoji_cell, parent, false)
        return Item(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp
                .with(context!!)
                .load(emojiName[position])
                .into((holder as Item).emojiView)
        holder.itemView.setOnClickListener {
            if (mClickListener != null) {
                mClickListener!!.onEmojiClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return emojiName.size
    }

    inner class Item(itemView: View) : ViewHolder(itemView) {
        var emojiView: ImageView

        init {
            emojiView = itemView.findViewById<View>(R.id.emojiView) as ImageView
        }
    }

    interface OnEmojiListener {
        fun onEmojiClick(position: Int)
    }

    fun setEmojiListener(mClickListener: OnEmojiListener?) {
        this.mClickListener = mClickListener
    }

}