package com.example.chatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener,MessageAdapter.OnItemListener,EmojiAdapter.OnEmojiListener
{
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private RecyclerView recyclerMsg,emojiPane;
    private MessageAdapter msgAdapter;
    private EmojiAdapter emojiAdapter;
    private RecyclerView.LayoutManager msgLayoutManager,emojiLayoutManager;
    com.wang.avi.AVLoadingIndicatorView firstType,secondType;
    EditText msg;
    static ImageButton sendBtn,attachmentBtn,menuBtn,item1,item2,item3,item4,item5;
    RelativeLayout menuBar,background,emojiRel;
    float d,SCREEN_WIDTH,SCREEN_HEIGHT;
    static int currentX,currentY,startX,startY,i=0,visible=0;
    boolean isEmojiPaneVisible=false;

    ArrayList<String> messages;
    ArrayList<Integer> sender;
    ArrayList<String> sentTime;
    ArrayList<String> sentDate;
    ArrayList<String> showdate;
    ArrayList<Integer> datePosition;
    static ArrayList<Integer> emojiName;
    RelativeLayout.LayoutParams menuBarParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d = this.getResources().getDisplayMetrics().density;
        SCREEN_WIDTH=getWindowManager().getDefaultDisplay().getWidth();
        SCREEN_HEIGHT=getWindowManager().getDefaultDisplay().getHeight();

        messages = new ArrayList<String>();
        sender = new ArrayList<Integer>();
        sentTime = new ArrayList<String>();
        sentDate = new ArrayList<String>();
        showdate = new ArrayList<String>();
        datePosition = new ArrayList<Integer>();
        emojiName=new ArrayList<Integer>();



        msg=(EditText)findViewById(R.id.msg);
        sendBtn=(ImageButton)findViewById(R.id.sendBtn);
        recyclerMsg=(RecyclerView)findViewById(R.id.recyclerMsg);
        emojiPane=(RecyclerView)findViewById(R.id.emojiPane);
        firstType=(com.wang.avi.AVLoadingIndicatorView)findViewById(R.id.firstType);
        secondType=(com.wang.avi.AVLoadingIndicatorView)findViewById(R.id.secondType);
        attachmentBtn=(ImageButton)findViewById(R.id.attachmentBtn);
        menuBar=(RelativeLayout)findViewById(R.id.menuBar);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);
        background=(RelativeLayout)findViewById(R.id.background);
        item1=(ImageButton)findViewById(R.id.item1);
        item2=(ImageButton)findViewById(R.id.item2);
        item3=(ImageButton)findViewById(R.id.item3);
        item4=(ImageButton)findViewById(R.id.item4);
        item5=(ImageButton)findViewById(R.id.item5);
        emojiRel=(RelativeLayout)findViewById(R.id.emojiRel);

        initImages(this);
        emojiName=EmojiInitializer.initialize();

        firstType.setVisibility(View.GONE);
        secondType.setVisibility(View.GONE);
        menuBar.setVisibility(View.GONE);
        emojiRel.setVisibility(View.GONE);


        msgAdapter=new MessageAdapter(messages,sender,sentTime,showdate);
        msgAdapter.setListener(this);
        msgLayoutManager=new LinearLayoutManager(this);
        recyclerMsg.setLayoutManager(msgLayoutManager);
        recyclerMsg.setHasFixedSize(true);
        recyclerMsg.setAdapter(msgAdapter);
        msgLayoutManager.scrollToPosition(messages.size()-1);

        emojiAdapter=new EmojiAdapter(emojiName);
        emojiAdapter.setEmojiListener(this);
        emojiLayoutManager=new GridLayoutManager(this,5);
        emojiPane.setLayoutManager(emojiLayoutManager);
        emojiPane.setHasFixedSize(true);
        emojiPane.setAdapter(emojiAdapter);
        emojiLayoutManager.scrollToPosition(0);

        sendBtn.setOnClickListener(this);
        msg.setOnFocusChangeListener(this);
        attachmentBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        background.setOnClickListener(this);
        recyclerMsg.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);

        menuBarParams=(RelativeLayout.LayoutParams)menuBar.getLayoutParams();
        menuBarParams.addRule(RelativeLayout.ABOVE,R.id.baseBar);
        menuBarParams.setMargins(0,0,0,0);
        menuBar.setLayoutParams(menuBarParams);


        KeyboardVisibilityEvent.setEventListener(
                MainActivity.this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen)
                    {
                        if(isOpen==false)
                        {
                            View current = getCurrentFocus();
                            if (current != null) {current.clearFocus();}
                        }
                    }
                });


    }


    @Override
    public void onClick(View v)
    {
        if(v.equals(sendBtn))
        {
            if(visible==1) {
                visible = 0;
                menuBar.setVisibility(View.GONE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            }
            String message=msg.getText().toString().trim();
            if(message.length()>0)
            {
                messages.add(message);
                sender.add(i);
                sentTime.add(getTime());
                adjustDates();
                msgLayoutManager.scrollToPosition(messages.size()-1);
                msg.setText("");
                recyclerMsg.requestFocus();

                if(i==0)
                    i=1;
                else
                    i=0;

                msg.requestFocus();
            }
        }
        else if(v.equals(attachmentBtn))
        {
            if(visible==1) {
                visible = 0;
                menuBar.setVisibility(View.GONE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            }
            //Accessing the image gallery
            Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
            File pictureDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String pictureDirectoryPath=pictureDirectory.getPath();
            Uri data=Uri.parse(pictureDirectoryPath);
            photoPickerIntent.setDataAndType(data,"image/*");
            startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
        }
        else if(v.equals(menuBtn))
        {
            if(visible==0)
            {
                visible=1;
                menuBar.setVisibility(View.VISIBLE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.cross);
            }
            else
            {
                visible=0;
                menuBar.setVisibility(View.GONE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            }
        }
        else if(v.equals(background))
        {
            if(visible==1) {
                visible = 0;
                menuBar.setVisibility(View.GONE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            }
        }
        else if(v.equals(recyclerMsg))
        {
            if(visible==1) {
                visible = 0;
                menuBar.setVisibility(View.GONE);
                loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            }
        }
        else if(v.equals(item1))
        {
            hideKeyboard(MainActivity.this);
            emojiRel.setVisibility(View.VISIBLE);
            isEmojiPaneVisible=true;
            menuBarParams=(RelativeLayout.LayoutParams)menuBar.getLayoutParams();
            menuBarParams.addRule(RelativeLayout.ABOVE,R.id.emojiRel);
            menuBarParams.setMargins(0,0,0,-1*(int)this.getResources().getDimension(R.dimen.emojiRelMarginTop));
            menuBar.setLayoutParams(menuBarParams);
        }
        else if(v.equals(item2))
        {

        }
        else if(v.equals(item3))
        {

        }
        else if(v.equals(item4))
        {

        }
        else if(v.equals(item5))
        {

        }
    }



    static void setCurrentX(int x)
    {
        currentX=x;
    }
    static void setCurrentY(int y)
    {
        currentY=y;
    }
    static void setStartX(int x)
    {
        startX=x;
    }
    static void setStartY(int y)
    {
        startY=y;
    }

    String getTime()
    {
        Calendar rightNow=Calendar.getInstance();
        int hr=rightNow.get(Calendar.HOUR);
        int hr2=rightNow.get(Calendar.HOUR_OF_DAY);
        int min=rightNow.get(Calendar.MINUTE);
        String half="";
        String time="";
        if(hr2>=12)
        {
            half = "PM";
        }
        else
            half="AM";
        if(min>=10)
            time=Integer.toString(hr)+":"+Integer.toString(min)+" "+half;
        else
            time=Integer.toString(hr)+":0"+Integer.toString(min)+" "+half;
        return(time);
    }

    String getDate()
    {
        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate=dateFormat.format(date);
        //Toast.makeText(MainActivity.this,""+formattedDate,Toast.LENGTH_SHORT).show();
        return formattedDate;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        if(v.equals(msg))
        {
            if(hasFocus)
            {
                if(i==0) {
                    firstType.setVisibility(View.VISIBLE);
                    secondType.setVisibility(View.GONE);
                }
                else
                {
                    firstType.setVisibility(View.GONE);
                    secondType.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                firstType.setVisibility(View.GONE);
                secondType.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();

                messages.add(imageUri.toString());
                if (i == 0)
                    sender.add(2);
                else
                    sender.add(3);
                sentTime.add(getTime());
                adjustDates();
                msgLayoutManager.scrollToPosition(messages.size() - 1);
                msg.setText("");
                recyclerMsg.requestFocus();

                if (i == 0)
                    i = 1;
                else
                    i = 0;

                msg.requestFocus();
            }
        }
    }

    @Override
    public void onItemClick(int position)
    {
        //Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        if(visible==1) {
            visible = 0;
            menuBar.setVisibility(View.GONE);
            loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
        }
    }

    public void adjustDates()
    {
        sentDate.add(getDate());
        if(sentDate.size()==1)
        {
            datePosition.add(messages.size()-1);
            showdate.add("Today");
        }
        else if(!sentDate.get(sentDate.size()-1).equals(sentDate.get(sentDate.size()-2)))
        {
            datePosition.add(messages.size()-1);
            showdate.add("Today");
            showdate.set(datePosition.size()-2,"Yesterday");
            if(showdate.size()-3>=0)
                showdate.set(datePosition.size()-3,sentDate.get(datePosition.size()-3));
        }
        else
        {
            showdate.add("");
        }
        msgAdapter.notifyItemInserted(messages.size()-1);
        if(datePosition.size()==1)
        {
            msgAdapter.notifyItemChanged(datePosition.get(0));
        }
        else if(datePosition.size()==2)
        {
            msgAdapter.notifyItemChanged(datePosition.get(0));
            msgAdapter.notifyItemChanged(datePosition.get(1));
        }
        else
        {
            msgAdapter.notifyItemChanged(datePosition.get(datePosition.size()-1));
            msgAdapter.notifyItemChanged(datePosition.get(datePosition.size()-2));
            msgAdapter.notifyItemChanged(datePosition.get(datePosition.size()-3));
        }
    }

    public static void initImages(Context context)
    {
        loadIconOrMenuImg(context,menuBtn,R.drawable.plus);
        loadIconOrMenuImg(context,sendBtn,R.drawable.send_btn);
        loadIconOrMenuImg(context,attachmentBtn,R.drawable.attachment);
        loadIconOrMenuImg(context,item1,R.drawable.right_thumbs_up);
        loadIconOrMenuImg(context,item2,R.drawable.font_logo);
        loadIconOrMenuImg(context,item3,R.drawable.placeholder);
        loadIconOrMenuImg(context,item4,R.drawable.placeholder);
        loadIconOrMenuImg(context,item5,R.drawable.placeholder);


    }

    public static void loadIconOrMenuImg(Context context,ImageButton imgBtn, int image)
    {
        GlideApp
                .with(context)
                .load(image)
                .into(imgBtn);
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onEmojiClick(int position)
    {
        if(i==0)
        {
            sender.add(6);
            messages.add(Integer.toString(emojiName.get(position)));
            i=1;
        }
        else
        {
            sender.add(7);
            messages.add(Integer.toString(emojiName.get(position)));
            i=0;
        }
        sentTime.add(getTime());
        adjustDates();
        msgLayoutManager.scrollToPosition(messages.size()-1);
        recyclerMsg.requestFocus();
        emojiRel.setVisibility(View.GONE);
        isEmojiPaneVisible=false;
        menuBar.setVisibility(View.GONE);
        visible=0;
        loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);

        menuBarParams=(RelativeLayout.LayoutParams)menuBar.getLayoutParams();
        menuBarParams.addRule(RelativeLayout.ABOVE,R.id.baseBar);
        menuBarParams.setMargins(0,0,0,0);
        menuBar.setLayoutParams(menuBarParams);
    }

    @Override
    public void onBackPressed()
    {
        if(isEmojiPaneVisible)
        {
            emojiRel.setVisibility(View.GONE);
            isEmojiPaneVisible=false;
            menuBarParams=(RelativeLayout.LayoutParams)menuBar.getLayoutParams();
            menuBarParams.addRule(RelativeLayout.ABOVE,R.id.baseBar);
            menuBarParams.setMargins(0,0,0,0);
            menuBar.setLayoutParams(menuBarParams);
        }
        else if(visible==1)
        {
            menuBar.setVisibility(View.GONE);
            loadIconOrMenuImg(MainActivity.this,menuBtn,R.drawable.plus);
            visible=0;
        }
        else
            super.onBackPressed();
    }
}
