package com.example.chatapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.makeramen.roundedimageview.RoundedImageView

class MessageAdapter(messages: ArrayList<String>?,
                     sender: ArrayList<Int>?,
                     sentTime: ArrayList<String>?,
                     showdate: ArrayList<String>?,
                     msgFont: ArrayList<Int>?,
                     quotes: ArrayList<String>?,
                     font: ArrayList<Int>?,
                     bg: ArrayList<Int>?,
                     fontColor: ArrayList<Int>?,
                     rotationAngle: ArrayList<Float>?
) : RecyclerView.Adapter<ViewHolder>() {
    var messages = ArrayList<String>()
    var sender = ArrayList<Int>()
    var sentTime = ArrayList<String>()
    var showdate = ArrayList<String>()
    var msgFont = ArrayList<Int>()
    var quotes = ArrayList<String>()
    var font = ArrayList<Int>()
    var bg = ArrayList<Int>()
    var fontColor = ArrayList<Int>()
    var rotationAngle = ArrayList<Float>()
    lateinit var myContext: Context
    private var mClickListener: OnItemListener? = null

    init {
        if (messages != null) {
            this.messages = messages
        }
        if (sender != null) {
            this.sender = sender
        }
        if (sentTime != null) {
            this.sentTime = sentTime
        }
        if (showdate != null) {
            this.showdate = showdate
        }
        if (msgFont != null) {
            this.msgFont = msgFont
        }
        if (quotes != null) {
            this.quotes = quotes
        }
        if (font != null) {
            this.font = font
        }
        if (bg != null) {
            this.bg = bg
        }
        if (fontColor != null) {
            this.fontColor = fontColor
        }
        if (rotationAngle != null) {
            this.rotationAngle = rotationAngle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        myContext = parent.context
        val viewHolder: ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            0 -> {
                val v1 = inflater.inflate(R.layout.first_person_msg, parent, false)
                RecyclerViewHolder1(v1)
            }
            1 -> {
                val v2 = inflater.inflate(R.layout.second_person_msg, parent, false)
                RecyclerViewHolder2(v2)
            }
            2 -> {
                val v3 = inflater.inflate(R.layout.first_person_img, parent, false)
                RecyclerImgViewHolder1(v3)
            }
            3 -> {
                val v4 = inflater.inflate(R.layout.second_person_img, parent, false)
                RecyclerImgViewHolder2(v4)
            }
            6 -> {
                val v7 = inflater.inflate(R.layout.first_person_like, parent, false)
                RecyclerLikeViewHolder1(v7)
            }
            7 -> {
                val v8 = inflater.inflate(R.layout.second_person_like, parent, false)
                RecyclerLikeViewHolder2(v8)
            }
            8 -> {
                val v9 = inflater.inflate(R.layout.frst_person_quote, parent, false)
                QuoteViewHolder1(v9)
            }
            9 -> {
                val v10 = inflater.inflate(R.layout.second_person_quote, parent, false)
                QuoteViewHolder2(v10)
            }
            else -> {
                val v = inflater.inflate(R.layout.first_person_msg, parent, false)
                RecyclerViewHolder1(v)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                val vh1 = holder as RecyclerViewHolder1
                configureRecyclerViewHolder1(vh1, position)
            }
            1 -> {
                val vh2 = holder as RecyclerViewHolder2
                configureRecyclerViewHolder2(vh2, position)
            }
            2 -> {
                val im1 = holder as RecyclerImgViewHolder1
                configureRecyclerImgViewHolder1(im1, position)
            }
            3 -> {
                val im2 = holder as RecyclerImgViewHolder2
                configureRecyclerImgViewHolder2(im2, position)
            }
            6 -> {
                val lv1 = holder as RecyclerLikeViewHolder1
                configureRecyclerLikeViewHolder1(lv1, position)
            }
            7 -> {
                val lv2 = holder as RecyclerLikeViewHolder2
                configureRecyclerLikeViewHolder2(lv2, position)
            }
            8 -> {
                val qt1 = holder as QuoteViewHolder1
                configureQuoteViewHolder1(qt1, position)
            }
            9 -> {
                val qt2 = holder as QuoteViewHolder2
                configureQuoteViewHolder2(qt2, position)
            }
        }
        holder.itemView.setOnClickListener {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class RecyclerViewHolder1(var view: View) : ViewHolder(view) {
        var textView: TextView
        var time: TextView
        var dateText: TextView
        var dateRel: RelativeLayout

        init {
            textView = view.findViewById<View>(R.id.text) as TextView
            time = view.findViewById<View>(R.id.time) as TextView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            dateText = view.findViewById<View>(R.id.dateText) as TextView
        }
    }

    class RecyclerViewHolder2(var view: View) : ViewHolder(view) {
        var textView: TextView
        var time: TextView
        var dateText: TextView
        var dateRel: RelativeLayout

        init {
            textView = view.findViewById<View>(R.id.text) as TextView
            time = view.findViewById<View>(R.id.time) as TextView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            dateText = view.findViewById<View>(R.id.dateText) as TextView
        }
    }

    class RecyclerImgViewHolder1(var view: View) : ViewHolder(view) {
        var textView: TextView
        var time: TextView
        var dateText: TextView
        var imgMsg: RoundedImageView
        var dateRel: RelativeLayout

        init {
            textView = view.findViewById<View>(R.id.text) as TextView
            time = view.findViewById<View>(R.id.time) as TextView
            imgMsg = view.findViewById<View>(R.id.imgMsg) as RoundedImageView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            dateText = view.findViewById<View>(R.id.dateText) as TextView
        }
    }

    class RecyclerImgViewHolder2(var view: View) : ViewHolder(view) {
        var textView: TextView
        var time: TextView
        var dateText: TextView
        var imgMsg: RoundedImageView
        var dateRel: RelativeLayout

        init {
            textView = view.findViewById<View>(R.id.text) as TextView
            time = view.findViewById<View>(R.id.time) as TextView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            imgMsg = view.findViewById<View>(R.id.imgMsg) as RoundedImageView
            dateText = view.findViewById<View>(R.id.dateText) as TextView
        }
    }

    class RecyclerLikeViewHolder1(var view: View) : ViewHolder(view) {
        var time: TextView
        var dateText: TextView
        var dateRel: RelativeLayout
        var thumb: ImageView

        init {
            time = view.findViewById<View>(R.id.time) as TextView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            dateText = view.findViewById<View>(R.id.dateText) as TextView
            thumb = view.findViewById<View>(R.id.thumb) as ImageView
        }
    }

    class RecyclerLikeViewHolder2(var view: View) : ViewHolder(view) {
        var time: TextView
        var dateText: TextView
        var dateRel: RelativeLayout
        var thumb: ImageView

        init {
            time = view.findViewById<View>(R.id.time) as TextView
            dateRel = view.findViewById<View>(R.id.dateRel) as RelativeLayout
            dateText = view.findViewById<View>(R.id.dateText) as TextView
            thumb = view.findViewById<View>(R.id.thumb) as ImageView
        }
    }

    class QuoteViewHolder1(var view: View) : ViewHolder(view) {
        var quoteText: TextView

        var qtTime: TextView
        var qtDateText: TextView
        var qtDateRel: RelativeLayout
        var quoteRel: RelativeLayout

        init {
//            textView = view.findViewById<View>(R.id.text) as TextView
            qtTime = view.findViewById<View>(R.id.qtTime) as TextView
            qtDateRel = view.findViewById<View>(R.id.qtDateRel) as RelativeLayout
            qtDateText = view.findViewById<View>(R.id.qtDateText) as TextView
            quoteText = itemView.findViewById(R.id.quoteText) as TextView
            quoteRel = itemView.findViewById(R.id.quoteRel) as RelativeLayout
        }
    }

    class QuoteViewHolder2(var view: View) : ViewHolder(view) {
        var quoteText: TextView

        var qtTime: TextView
        var qtDateText: TextView
        var qtDateRel: RelativeLayout
        var quoteRel: RelativeLayout

        init {
//            textView = view.findViewById<View>(R.id.text) as TextView
            qtTime = view.findViewById<View>(R.id.qtTime) as TextView
            qtDateRel = view.findViewById<View>(R.id.qtDateRel) as RelativeLayout
            qtDateText = view.findViewById<View>(R.id.qtDateText) as TextView
            quoteText = itemView.findViewById(R.id.quoteText) as TextView
            quoteRel = itemView.findViewById(R.id.quoteRel) as RelativeLayout
        }
    }

    override fun getItemViewType(position: Int): Int {
        return sender[position]
    }

    private fun configureRecyclerViewHolder1(vh1: RecyclerViewHolder1, position: Int) {
        vh1.textView.text = messages[position] + " \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0"
        vh1.textView.setTextColor(Color.BLACK)
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, msgFont[position])
        vh1?.textView.typeface = typeFace
        vh1.time.text = sentTime[position]
        vh1.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            vh1.dateRel.visibility = View.VISIBLE
        }
        vh1.dateText.text = "   " + showdate[position] + "   "
    }

    private fun configureRecyclerViewHolder2(vh2: RecyclerViewHolder2, position: Int) {
        vh2.textView.text = messages[position] + " \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0"
        vh2.textView.setTextColor(Color.BLACK)
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, msgFont[position])
        vh2?.textView.typeface = typeFace
        vh2.time.text = sentTime[position]
        vh2.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            vh2.dateRel.visibility = View.VISIBLE
        }
        vh2.dateText.text = "   " + showdate[position] + "   "
    }

    private fun configureRecyclerImgViewHolder1(im1: RecyclerImgViewHolder1, position: Int) {
        im1.textView.text = " \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0"
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, msgFont[position])
        im1?.textView.typeface = typeFace
        im1.time.text = sentTime[position]
        GlideApp
                .with(myContext!!)
                .load(Uri.parse(messages[position]))
                .into(im1.imgMsg)
        im1.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            im1.dateRel.visibility = View.VISIBLE
        }
        im1.dateText.text = "   " + showdate[position] + "   "
    }

    private fun configureRecyclerImgViewHolder2(im2: RecyclerImgViewHolder2, position: Int) {
        im2.textView.text = " \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0"
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, msgFont[position])
        im2?.textView.typeface = typeFace
        im2.time.text = sentTime[position]
        GlideApp
                .with(myContext!!)
                .load(Uri.parse(messages[position]))
                .into(im2.imgMsg)
        im2.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            im2.dateRel.visibility = View.VISIBLE
        }
        im2.dateText.text = "   " + showdate[position] + "   "
    }

    private fun configureRecyclerLikeViewHolder1(lv1: RecyclerLikeViewHolder1, position: Int) {
        lv1.time.text = sentTime[position]
        lv1.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            lv1.dateRel.visibility = View.VISIBLE
        }
        lv1.dateText.text = "   " + showdate[position] + "   "
        GlideApp
                .with(myContext!!)
                .load(messages[position].toInt())
                .into(lv1.thumb)
    }

    private fun configureRecyclerLikeViewHolder2(lv2: RecyclerLikeViewHolder2, position: Int) {
        lv2.time.text = sentTime[position]
        lv2.dateRel.visibility = View.GONE
        if (showdate[position] != "") {
            lv2.dateRel.visibility = View.VISIBLE
        }
        lv2.dateText.text = "   " + showdate[position] + "   "
        GlideApp
                .with(myContext!!)
                .load(messages[position].toInt())
                .into(lv2.thumb)
    }

    private fun configureQuoteViewHolder1(qt1: QuoteViewHolder1, position: Int) {
        qt1?.quoteText?.text = quotes.get(position).toString()
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, font.get(position))
        qt1?.quoteText.setTypeface(typeFace, Typeface.ITALIC)
        qt1?.quoteRel.setBackgroundResource(bg.get(position))
        qt1?.quoteText.setTextColor(myContext.resources.getColor(fontColor.get(position)))
        qt1?.quoteText.rotation = rotationAngle.get(position)
        if (quotes.get(position).length < 60)
            qt1?.quoteText.textSize = 30f / (quotes.get(position).length / 40f)
        else
            qt1?.quoteText.textSize = 20f

        qt1.qtTime.text = sentTime[position]
        qt1.qtDateRel.visibility = View.GONE
        if (showdate[position] != "") {
            qt1.qtDateRel.visibility = View.VISIBLE
        }
        qt1.qtDateText.text = "   " + showdate[position] + "   "
    }

    private fun configureQuoteViewHolder2(qt2: QuoteViewHolder2, position: Int) {
        qt2?.quoteText?.text = quotes.get(position).toString()
        var typeFace: Typeface? = ResourcesCompat.getFont(myContext, font.get(position))
        qt2?.quoteText.setTypeface(typeFace, Typeface.ITALIC)
        qt2?.quoteRel.setBackgroundResource(bg.get(position))
        qt2?.quoteText.setTextColor(myContext.resources.getColor(fontColor.get(position)))
        qt2?.quoteText.rotation = rotationAngle.get(position)
        if (quotes.get(position).length < 60)
            qt2?.quoteText.textSize = 30f / (quotes.get(position).length / 40f)
        else
            qt2?.quoteText.textSize = 20f

        qt2.qtTime.text = sentTime[position]
        qt2.qtDateRel.visibility = View.GONE
        if (showdate[position] != "") {
            qt2.qtDateRel.visibility = View.VISIBLE
        }
        qt2.qtDateText.text = "   " + showdate[position] + "   "
    }

    interface OnItemListener {
        fun onItemClick(position: Int)
    }

}