package com.example.chatapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmojiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    ArrayList<Integer> emojiName;
    private OnEmojiListener mClickListener;
    public EmojiAdapter( ArrayList<Integer> emojiName)
    {
        this.emojiName=emojiName;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View cell=inflater.inflate(R.layout.emoji_cell,parent,false);
        Item item=new Item(cell);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        GlideApp
                .with(context)
                .load(emojiName.get(position))
                .into(((Item)holder).emojiView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener!=null)
                {
                    mClickListener.onEmojiClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return emojiName.size();
    }
    public class Item extends RecyclerView.ViewHolder
    {
        ImageView emojiView;
        public Item(View itemView)
        {
            super(itemView);
            emojiView=(ImageView)itemView.findViewById(R.id.emojiView);
        }
    }

    public interface OnEmojiListener
    {
        public void onEmojiClick(int position);
    }
    public void setEmojiListener(EmojiAdapter.OnEmojiListener mClickListener)
    {
        this.mClickListener=mClickListener;
    }
}
