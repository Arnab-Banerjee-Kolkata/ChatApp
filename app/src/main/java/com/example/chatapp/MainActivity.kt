package com.example.chatapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.EmojiAdapter.OnEmojiListener
import com.example.chatapp.MessageAdapter.OnItemListener
import com.wang.avi.AVLoadingIndicatorView
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),
        View.OnClickListener,
        OnFocusChangeListener,
        OnItemListener,
        OnEmojiListener,
        PopupMenu.OnMenuItemClickListener {
    private var recyclerMsg: RecyclerView? = null
    private var emojiPane: RecyclerView? = null
    private var msgAdapter: MessageAdapter? = null
    private var emojiAdapter: EmojiAdapter? = null
    private var qtAdapter: MessageAdapter? = null
    private var msgLayoutManager: RecyclerView.LayoutManager? = null
    private var emojiLayoutManager: RecyclerView.LayoutManager? = null
    private var quoteLayoutManager: RecyclerView.LayoutManager? = null
    var firstType: AVLoadingIndicatorView? = null
    var secondType: AVLoadingIndicatorView? = null
    var msg: EditText? = null
    var menuBar: RelativeLayout? = null
    var background: RelativeLayout? = null
    var emojiRel: RelativeLayout? = null
    var quoteBtn: ImageButton? = null
    var d = 0f
    var SCREEN_WIDTH = 0f
    var SCREEN_HEIGHT = 0f
    var isEmojiPaneVisible = false

    var messages: ArrayList<String>? = null
    var sender: ArrayList<Int>? = null
    var sentTime: ArrayList<String>? = null
    var sentDate: ArrayList<String>? = null
    var showdate: ArrayList<String>? = null
    var datePosition: ArrayList<Int>? = null
    lateinit var msgFont: ArrayList<Int>
    var menuBarParams: RelativeLayout.LayoutParams? = null

    lateinit var msgView: RecyclerView
    lateinit var quotes: ArrayList<String>
    lateinit var font: ArrayList<Int>
    lateinit var bg: ArrayList<Int>
    lateinit var fontColor: ArrayList<Int>
    lateinit var rotationAngle: ArrayList<Float>

    val QUOTE_PICKER_ID = 12;
    var i = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        d = this.resources.displayMetrics.density
        SCREEN_WIDTH = windowManager.defaultDisplay.width.toFloat()
        SCREEN_HEIGHT = windowManager.defaultDisplay.height.toFloat()

        messages = ArrayList()
        sender = ArrayList()
        sentTime = ArrayList()
        sentDate = ArrayList()
        showdate = ArrayList()
        datePosition = ArrayList()
        emojiName = ArrayList()
        msgFont = ArrayList<Int>()
        quotes = ArrayList<String>()
        font = ArrayList<Int>()
        bg = ArrayList<Int>()
        fontColor = ArrayList<Int>()
        rotationAngle = ArrayList<Float>()

        msg = findViewById<View>(R.id.msg) as EditText
        sendBtn = findViewById<View>(R.id.sendBtn) as ImageButton
        recyclerMsg = findViewById<View>(R.id.recyclerMsg) as RecyclerView
        emojiPane = findViewById<View>(R.id.emojiPane) as RecyclerView
        firstType = findViewById<View>(R.id.firstType) as AVLoadingIndicatorView
        secondType = findViewById<View>(R.id.secondType) as AVLoadingIndicatorView
        attachmentBtn = findViewById<View>(R.id.attachmentBtn) as ImageButton
        menuBar = findViewById<View>(R.id.menuBar) as RelativeLayout
        menuBtn = findViewById<View>(R.id.menuBtn) as ImageButton
        background = findViewById<View>(R.id.background) as RelativeLayout
        item1 = findViewById<View>(R.id.item1) as ImageButton
        item2 = findViewById<View>(R.id.item2) as ImageButton
        item3 = findViewById<View>(R.id.item3) as ImageButton
        item4 = findViewById<View>(R.id.item4) as ImageButton
        item5 = findViewById<View>(R.id.item5) as ImageButton
        emojiRel = findViewById<View>(R.id.emojiRel) as RelativeLayout
        setting = findViewById(R.id.setting) as ImageButton
        msgView = findViewById(R.id.recyclerMsg) as RecyclerView
        quoteBtn = findViewById(R.id.quoteBtn) as ImageButton

        quoteLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        msgView.layoutManager = quoteLayoutManager
        msgView.setHasFixedSize(true)
        qtAdapter = MessageAdapter(null, sender!!, null, null, null, quotes, font, bg, fontColor, rotationAngle)
        msgView.adapter = qtAdapter

        quoteBtn!!.setOnClickListener(this)

        initImages(this)

        emojiName = EmojiInitializer.initialize()
        firstType!!.visibility = View.GONE
        secondType!!.visibility = View.GONE
        menuBar!!.visibility = View.GONE
        emojiRel!!.visibility = View.GONE

        msgAdapter = MessageAdapter(messages, sender, sentTime, showdate, msgFont,
                quotes, font, bg, fontColor, rotationAngle)
        msgAdapter!!.setListener(this)
        msgLayoutManager = LinearLayoutManager(this)
        recyclerMsg!!.layoutManager = msgLayoutManager
        recyclerMsg!!.setHasFixedSize(true)
        recyclerMsg!!.adapter = msgAdapter
        (msgLayoutManager as LinearLayoutManager).scrollToPosition(messages!!.size - 1)

        emojiAdapter = emojiName?.let { EmojiAdapter(it) }
        emojiAdapter!!.setEmojiListener(this)
        emojiLayoutManager = GridLayoutManager(this, 5)
        emojiPane!!.layoutManager = emojiLayoutManager
        emojiPane!!.setHasFixedSize(true)
        emojiPane!!.adapter = emojiAdapter
        (emojiLayoutManager as GridLayoutManager).scrollToPosition(0)

        sendBtn!!.setOnClickListener(this)
        msg!!.onFocusChangeListener = this
        attachmentBtn!!.setOnClickListener(this)
        menuBtn!!.setOnClickListener(this)
        background!!.setOnClickListener(this)
        recyclerMsg!!.setOnClickListener(this)
        item1!!.setOnClickListener(this)
        item2!!.setOnClickListener(this)
        item3!!.setOnClickListener(this)
        item4!!.setOnClickListener(this)
        item5!!.setOnClickListener(this)
        setting.setOnClickListener(this)

        menuBarParams = menuBar!!.layoutParams as RelativeLayout.LayoutParams
        menuBarParams!!.addRule(RelativeLayout.ABOVE, R.id.baseBar)
        menuBarParams!!.setMargins(0, 0, 0, 0)
        menuBar!!.layoutParams = menuBarParams


        KeyboardVisibilityEvent.setEventListener(
                this@MainActivity
        ) { isOpen ->
            if (isOpen == false) {
                val current = currentFocus
                current?.clearFocus()
            }
        }
    }

    override fun onClick(v: View) {
        if (v == sendBtn) {
            if (visible == 1) {
                visible = 0
                menuBar!!.visibility = View.GONE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            }
            val message = msg!!.text.toString().trim { it <= ' ' }
            if (message.length > 0) {
                messages!!.add(message)
                sender!!.add(i)
                sentTime!!.add(time)
                msgFont.add(defaultFont)
                adjustDates()

                quotes.add("")
                font.add(-1)
                bg.add(-1)
                fontColor.add(-1)
                rotationAngle.add(-1f)

                msgLayoutManager!!.scrollToPosition(messages!!.size - 1)
                msgAdapter!!.notifyItemInserted(messages!!.size - 1)
                msg!!.setText("")
                recyclerMsg!!.requestFocus()
                i = if (i == 0) 1 else 0
                msg!!.requestFocus()
            }
        } else if (v == attachmentBtn) {
            if (visible == 1) {
                visible = 0
                menuBar!!.visibility = View.GONE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            }
            //Accessing the image gallery
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            val pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val pictureDirectoryPath = pictureDirectory.path
            val data = Uri.parse(pictureDirectoryPath)
            photoPickerIntent.setDataAndType(data, "image/*")
            startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST)
        } else if (v == menuBtn) {
            if (visible == 0) {
                visible = 1
                menuBar!!.visibility = View.VISIBLE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.cross)
            } else {
                visible = 0
                menuBar!!.visibility = View.GONE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            }
        } else if (v == background) {
            if (visible == 1) {
                visible = 0
                menuBar!!.visibility = View.GONE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            }
        } else if (v == recyclerMsg) {
            if (visible == 1) {
                visible = 0
                menuBar!!.visibility = View.GONE
                loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            }
        } else if (v == item1) {
            hideKeyboard(this@MainActivity)
            emojiRel!!.visibility = View.VISIBLE
            isEmojiPaneVisible = true
            menuBarParams = menuBar!!.layoutParams as RelativeLayout.LayoutParams
            menuBarParams!!.addRule(RelativeLayout.ABOVE, R.id.emojiRel)
            menuBarParams!!.setMargins(0, 0, 0, -1 * this.resources.getDimension(R.dimen.emojiRelMarginTop).toInt())
            menuBar!!.layoutParams = menuBarParams
        } else if (v == item2) {
        } else if (v == item3) {
        } else if (v == item4) {
        } else if (v == item5) {
        } else if (v == setting) {
            PopupMenu(this, v).apply {
                setOnMenuItemClickListener(this@MainActivity)
                inflate(R.menu.options_menu)
                show()
            }
        } else if (v == quoteBtn) {
            val intent = Intent(applicationContext, SelectQuote::class.java)
            startActivityForResult(intent, QUOTE_PICKER_ID)
        }
    }

    val time: String
        get() {
            val rightNow = Calendar.getInstance()
            val hr = rightNow[Calendar.HOUR]
            val hr2 = rightNow[Calendar.HOUR_OF_DAY]
            val min = rightNow[Calendar.MINUTE]
            var half = ""
            var time = ""
            half = if (hr2 >= 12) {
                "PM"
            } else "AM"
            time = if (min >= 10) Integer.toString(hr) + ":" + Integer.toString(min) + " " + half else Integer.toString(hr) + ":0" + Integer.toString(min) + " " + half
            return time
        }

    //Toast.makeText(MainActivity.this,""+formattedDate,Toast.LENGTH_SHORT).show();
    val date: String
        get() {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
            //Toast.makeText(MainActivity.this,""+formattedDate,Toast.LENGTH_SHORT).show();
            return dateFormat.format(date)
        }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (v == msg) {
            if (hasFocus) {
                if (i == 0) {
                    firstType!!.visibility = View.VISIBLE
                    secondType!!.visibility = View.GONE
                } else {
                    firstType!!.visibility = View.GONE
                    secondType!!.visibility = View.VISIBLE
                }
            } else {
                firstType!!.visibility = View.GONE
                secondType!!.visibility = View.GONE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                val imageUri = data!!.data
                messages!!.add(imageUri.toString())
                if (i == 0) sender!!.add(2) else sender!!.add(3)
                msgFont.add(defaultFont)
                sentTime!!.add(time)
                adjustDates()

                quotes.add("")
                font.add(-1)
                bg.add(-1)
                fontColor.add(-1)
                rotationAngle.add(-1f)

                msgLayoutManager!!.scrollToPosition(messages!!.size - 1)
                msgAdapter!!.notifyItemInserted(messages!!.size - 1)
                msg!!.setText("")
                recyclerMsg!!.requestFocus()
                i = if (i == 0) 1 else 0
                msg!!.requestFocus()
            } else if (requestCode == FONT_REQUEST) {
                var intent: Intent = data as Intent
                defaultFont = intent.getIntExtra("selectedFont", 1)
            } else if (requestCode == QUOTE_PICKER_ID) {
                var myIntent: Intent = data as Intent
                var str: String = myIntent.getStringExtra("selectedQuote")!!
                //Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
                if (i == 0) {
                    sender?.add(8)
                    i = 1
                } else {
                    sender?.add(9)
                    i = 0
                }
                quotes.add(str)
                font.add(myIntent.getIntExtra("font", 1))
                bg.add(myIntent.getIntExtra("selectedBg", 1))
                fontColor.add(myIntent.getIntExtra("selectedFontColor", 1))
                rotationAngle.add(myIntent.getFloatExtra("selectedRotation", 1f))
//                qtAdapter?.notifyItemInserted(quotes.size-1)
//                quoteLayoutManager?.scrollToPosition(quotes.size-1)
                messages!!.add("-1");
                msgFont.add(defaultFont)
                sentTime!!.add(time)
                adjustDates()
                msgAdapter?.notifyItemInserted(messages!!.size - 1)
                msgLayoutManager!!.scrollToPosition(messages!!.size - 1)
                recyclerMsg!!.requestFocus()
            }
        }
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        if (visible == 1) {
            visible = 0
            menuBar!!.visibility = View.GONE
            loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
        }
    }

    fun adjustDates() {
        sentDate!!.add(date)
        if (sentDate!!.size == 1) {
            datePosition!!.add(messages!!.size - 1)
            showdate!!.add("Today")
        } else if (sentDate!![sentDate!!.size - 1] != sentDate!![sentDate!!.size - 2]) {
            datePosition!!.add(messages!!.size - 1)
            showdate!!.add("Today")
            showdate!![datePosition!!.size - 2] = "Yesterday"
            if (showdate!!.size - 3 >= 0) showdate!![datePosition!!.size - 3] = sentDate!![datePosition!!.size - 3]
        } else {
            showdate!!.add("")
        }
        msgAdapter!!.notifyItemInserted(messages!!.size - 1)
        if (datePosition!!.size == 1) {
            msgAdapter!!.notifyItemChanged(datePosition!![0])
        } else if (datePosition!!.size == 2) {
            msgAdapter!!.notifyItemChanged(datePosition!![0])
            msgAdapter!!.notifyItemChanged(datePosition!![1])
        } else {
            msgAdapter!!.notifyItemChanged(datePosition!![datePosition!!.size - 1])
            msgAdapter!!.notifyItemChanged(datePosition!![datePosition!!.size - 2])
            msgAdapter!!.notifyItemChanged(datePosition!![datePosition!!.size - 3])
        }
    }

    override fun onEmojiClick(position: Int) {
        i = if (i == 0) {
            sender!!.add(6)
            messages!!.add(Integer.toString(emojiName!![position]))
            1
        } else {
            sender!!.add(7)
            messages!!.add(Integer.toString(emojiName!![position]))
            0
        }
        msgFont.add(defaultFont)
        sentTime!!.add(time)
        adjustDates()

        quotes.add("")
        font.add(-1)
        bg.add(-1)
        fontColor.add(-1)
        rotationAngle.add(-1f)

        msgLayoutManager!!.scrollToPosition(messages!!.size - 1)
        recyclerMsg!!.requestFocus()
        emojiRel!!.visibility = View.GONE
        isEmojiPaneVisible = false
        menuBar!!.visibility = View.GONE
        visible = 0
        loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
        menuBarParams = menuBar!!.layoutParams as RelativeLayout.LayoutParams
        menuBarParams!!.addRule(RelativeLayout.ABOVE, R.id.baseBar)
        menuBarParams!!.setMargins(0, 0, 0, 0)
        menuBar!!.layoutParams = menuBarParams
    }

    override fun onBackPressed() {
        if (isEmojiPaneVisible) {
            emojiRel!!.visibility = View.GONE
            isEmojiPaneVisible = false
            menuBarParams = menuBar!!.layoutParams as RelativeLayout.LayoutParams
            menuBarParams!!.addRule(RelativeLayout.ABOVE, R.id.baseBar)
            menuBarParams!!.setMargins(0, 0, 0, 0)
            menuBar!!.layoutParams = menuBarParams
        } else if (visible == 1) {
            menuBar!!.visibility = View.GONE
            loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
            visible = 0
        } else super.onBackPressed()
    }

    companion object {
        const val IMAGE_GALLERY_REQUEST = 20
        var sendBtn: ImageButton? = null
        var attachmentBtn: ImageButton? = null
        var menuBtn: ImageButton? = null
        var item1: ImageButton? = null
        var item2: ImageButton? = null
        var item3: ImageButton? = null
        var item4: ImageButton? = null
        var item5: ImageButton? = null
        lateinit var setting: ImageButton
        private var currentX = 0
        private var currentY = 0
        private var startX = 0
        private var startY = 0
        var i = 0
        var visible = 0
        var emojiName: ArrayList<Int>? = null
        val FONT_REQUEST = 42
        var defaultFont = R.font.roboto


        fun setCurrentX(x: Int) {
            currentX = x
        }

        fun setCurrentY(y: Int) {
            currentY = y
        }

        fun setStartX(x: Int) {
            startX = x
        }

        fun setStartY(y: Int) {
            startY = y
        }

        fun initImages(context: Context?) {
            loadIconOrMenuImg(context, menuBtn, R.drawable.plus)
            loadIconOrMenuImg(context, sendBtn, R.drawable.send_btn)
            loadIconOrMenuImg(context, attachmentBtn, R.drawable.attachment)
            loadIconOrMenuImg(context, item1, R.drawable.right_thumbs_up)
            loadIconOrMenuImg(context, item2, R.drawable.font_logo)
            loadIconOrMenuImg(context, item3, R.drawable.placeholder)
            loadIconOrMenuImg(context, item4, R.drawable.placeholder)
            loadIconOrMenuImg(context, item5, R.drawable.placeholder)
        }

        fun loadIconOrMenuImg(context: Context?, imgBtn: ImageButton?, image: Int) {
            GlideApp
                    .with(context!!)
                    .load(image)
                    .into(imgBtn!!)
        }

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.changeFont -> {
                if (visible == 1) {
                    visible = 0
                    menuBar?.visibility = View.GONE
                    loadIconOrMenuImg(this@MainActivity, menuBtn, R.drawable.plus)
                }
                val intent: Intent = Intent(applicationContext, SelectFont::class.java)
                startActivityForResult(intent, FONT_REQUEST)
                true
            }
            else -> false
        }
    }
}