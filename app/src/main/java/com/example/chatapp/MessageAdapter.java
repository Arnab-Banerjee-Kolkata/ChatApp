package com.example.chatapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    ArrayList<String> messages=new ArrayList<String>();
    ArrayList<Integer> sender=new ArrayList<Integer>();
    ArrayList<String> sentTime=new ArrayList<String>();
    ArrayList<String> showdate=new ArrayList<String>();
    Context myContext;
    private OnItemListener mClickListener;


    public MessageAdapter(ArrayList<String> messages, ArrayList<Integer> sender, ArrayList<String> sentTime, ArrayList<String> showdate)
    {
        this.messages=messages;
        this.sender=sender;
        this.sentTime=sentTime;
        this.showdate=showdate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        myContext=parent.getContext();
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case 0:
                View v1=inflater.inflate(R.layout.first_person_msg,parent,false);
                viewHolder=new RecyclerViewHolder1(v1);
                break;
            case 1:
                View v2=inflater.inflate(R.layout.second_person_msg,parent,false);
                viewHolder=new RecyclerViewHolder2(v2);
                break;
            case 2:
                View v3=inflater.inflate(R.layout.first_person_img,parent,false);
                viewHolder=new RecyclerImgViewHolder1(v3);
                break;
            case 3:
                View v4=inflater.inflate(R.layout.second_person_img,parent,false);
                viewHolder=new RecyclerImgViewHolder2(v4);
                break;
            case 6:
                View v7=inflater.inflate(R.layout.first_person_like,parent,false);
                viewHolder=new RecyclerLikeViewHolder1(v7);
                break;
            case 7:
                View v8=inflater.inflate(R.layout.second_person_like,parent,false);
                viewHolder=new RecyclerLikeViewHolder2(v8);
                break;
            default:
                View v=inflater.inflate(R.layout.first_person_msg,parent,false);
                viewHolder=new RecyclerViewHolder1(v);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        switch(holder.getItemViewType())
        {
            case 0:
                RecyclerViewHolder1 vh1=(RecyclerViewHolder1)holder;
                configureRecyclerViewHolder1(vh1,position);
                break;
            case 1:
                RecyclerViewHolder2 vh2=(RecyclerViewHolder2)holder;
                configureRecyclerViewHolder2(vh2,position);
                break;
            case 2:
                RecyclerImgViewHolder1 im1=(RecyclerImgViewHolder1)holder;
                configureRecyclerImgViewHolder1(im1,position);
                break;
            case 3:
                RecyclerImgViewHolder2 im2=(RecyclerImgViewHolder2)holder;
                configureRecyclerImgViewHolder2(im2,position);
                break;
            case 6:
                RecyclerLikeViewHolder1 lv1=(RecyclerLikeViewHolder1)holder;
                configureRecyclerLikeViewHolder1(lv1,position);
                break;
            case 7:
                RecyclerLikeViewHolder2 lv2=(RecyclerLikeViewHolder2)holder;
                configureRecyclerLikeViewHolder2(lv2,position);
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener!=null)
                {
                    mClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class RecyclerViewHolder1 extends RecyclerView.ViewHolder
    {
        View view;
        TextView text,time,dateText;
        RelativeLayout dateRel;
        public RecyclerViewHolder1(View view)
        {
            super(view);
            this.view=view;
            text=(TextView)view.findViewById(R.id.text);
            time=(TextView)view.findViewById(R.id.time);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            dateText=(TextView)view.findViewById(R.id.dateText);
        }

        public TextView getTextView() {
            return text;
        }

        public TextView getTime() {
            return time;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }
    }

    public static class RecyclerViewHolder2 extends RecyclerView.ViewHolder
    {
        View view;
        TextView text,time,dateText;
        RelativeLayout dateRel;
        public RecyclerViewHolder2(View view)
        {
            super(view);
            this.view=view;
            text=(TextView)view.findViewById(R.id.text);
            time=(TextView)view.findViewById(R.id.time);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            dateText=(TextView)view.findViewById(R.id.dateText);
        }

        public TextView getTextView() {
            return text;
        }

        public TextView getTime() {
            return time;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }
    }
    public static class RecyclerImgViewHolder1 extends RecyclerView.ViewHolder
    {
        View view;
        TextView text,time,dateText;
        com.makeramen.roundedimageview.RoundedImageView imgMsg;
        RelativeLayout dateRel;
        public RecyclerImgViewHolder1(View view)
        {
            super(view);
            this.view=view;
            text=(TextView)view.findViewById(R.id.text);
            time=(TextView)view.findViewById(R.id.time);
            imgMsg=(com.makeramen.roundedimageview.RoundedImageView)view.findViewById(R.id.imgMsg);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            dateText=(TextView)view.findViewById(R.id.dateText);
        }

        public TextView getTime() {
            return time;
        }

        public TextView getTextView() {
            return text;
        }

        public com.makeramen.roundedimageview.RoundedImageView getImgMsg() {
            return imgMsg;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }
    }
    public static class RecyclerImgViewHolder2 extends RecyclerView.ViewHolder
    {
        View view;
        TextView text,time,dateText;
        com.makeramen.roundedimageview.RoundedImageView imgMsg;
        RelativeLayout dateRel;
        public RecyclerImgViewHolder2(View view)
        {
            super(view);
            this.view=view;
            text=(TextView)view.findViewById(R.id.text);
            time=(TextView)view.findViewById(R.id.time);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            imgMsg=(com.makeramen.roundedimageview.RoundedImageView)view.findViewById(R.id.imgMsg);
            dateText=(TextView)view.findViewById(R.id.dateText);
        }

        public TextView getTime() {
            return time;
        }

        public TextView getTextView() {
            return text;
        }

        public com.makeramen.roundedimageview.RoundedImageView getImgMsg() {
            return imgMsg;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }
    }
    public static class RecyclerLikeViewHolder1 extends RecyclerView.ViewHolder
    {
        View view;
        TextView time,dateText;
        RelativeLayout dateRel;
        ImageView thumb;
        public RecyclerLikeViewHolder1(View view)
        {
            super(view);
            this.view=view;
            time=(TextView)view.findViewById(R.id.time);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            dateText=(TextView)view.findViewById(R.id.dateText);
            thumb=(ImageView)view.findViewById(R.id.thumb);
        }

        public TextView getTime() {
            return time;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }

        public ImageView getThumb() {
            return thumb;
        }
    }
    public static class RecyclerLikeViewHolder2 extends RecyclerView.ViewHolder
    {
        View view;
        TextView time,dateText;
        RelativeLayout dateRel;
        ImageView thumb;
        public RecyclerLikeViewHolder2(View view)
        {
            super(view);
            this.view=view;
            time=(TextView)view.findViewById(R.id.time);
            dateRel=(RelativeLayout)view.findViewById(R.id.dateRel);
            dateText=(TextView)view.findViewById(R.id.dateText);
            thumb=(ImageView)view.findViewById(R.id.thumb);
        }

        public TextView getTime() {
            return time;
        }

        public RelativeLayout getDateRel() {
            return dateRel;
        }

        public TextView getDateText() {
            return dateText;
        }

        public ImageView getThumb() {
            return thumb;
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return sender.get(position);
    }

    private void configureRecyclerViewHolder1(RecyclerViewHolder1 vh1,int position)
    {
        vh1.getTextView().setText(messages.get(position)+" \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0");
        vh1.getTextView().setTextColor(Color.BLACK);
        vh1.getTime().setText(sentTime.get(position));
        vh1.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            vh1.getDateRel().setVisibility(View.VISIBLE);
        }
        vh1.getDateText().setText("   "+showdate.get(position)+"   ");
    }

    private void configureRecyclerViewHolder2(RecyclerViewHolder2 vh2,int position)
    {
        vh2.getTextView().setText(messages.get(position)+" \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0");
        vh2.getTextView().setTextColor(Color.BLACK);
        vh2.getTime().setText(sentTime.get(position));
        vh2.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            vh2.getDateRel().setVisibility(View.VISIBLE);
        }
        vh2.getDateText().setText("   "+showdate.get(position)+"   ");
    }
    private void configureRecyclerImgViewHolder1(RecyclerImgViewHolder1 im1, int position)
    {
        im1.getTextView().setText(" \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0");
        im1.getTime().setText(sentTime.get(position));

        GlideApp
                .with(myContext)
                .load(Uri.parse(messages.get(position)))
                .into(im1.getImgMsg());

        im1.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            im1.getDateRel().setVisibility(View.VISIBLE);
        }
        im1.getDateText().setText("   "+showdate.get(position)+"   ");
    }
    private void configureRecyclerImgViewHolder2(RecyclerImgViewHolder2 im2, int position)
    {
        im2.getTextView().setText(" \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0");
        im2.getTime().setText(sentTime.get(position));

        GlideApp
                .with(myContext)
                .load(Uri.parse(messages.get(position)))
                .into(im2.getImgMsg());

        im2.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            im2.getDateRel().setVisibility(View.VISIBLE);
        }
        im2.getDateText().setText("   "+showdate.get(position)+"   ");
    }
    private void configureRecyclerLikeViewHolder1(RecyclerLikeViewHolder1 lv1, int position)
    {
        lv1.getTime().setText(sentTime.get(position));
        lv1.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            lv1.getDateRel().setVisibility(View.VISIBLE);
        }
        lv1.getDateText().setText("   "+showdate.get(position)+"   ");
        GlideApp
                .with(myContext)
                .load(Integer.parseInt(messages.get(position)))
                .into(lv1.getThumb());
    }
    private void configureRecyclerLikeViewHolder2(RecyclerLikeViewHolder2 lv2, int position)
    {
        lv2.getTime().setText(sentTime.get(position));
        lv2.getDateRel().setVisibility(View.GONE);
        if(!showdate.get(position).equals(""))
        {
            lv2.getDateRel().setVisibility(View.VISIBLE);
        }
        lv2.getDateText().setText("   "+showdate.get(position)+"   ");
        GlideApp
                .with(myContext)
                .load(Integer.parseInt(messages.get(position)))
                .into(lv2.getThumb());
    }

    public interface OnItemListener
    {
        public void onItemClick(int position);
    }
    public void setListener(MessageAdapter.OnItemListener mClickListener)
    {
        this.mClickListener=mClickListener;
    }
}
