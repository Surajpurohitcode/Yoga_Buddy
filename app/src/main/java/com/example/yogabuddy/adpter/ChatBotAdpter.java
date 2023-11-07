package com.example.yogabuddy.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yogabuddy.R;
import com.example.yogabuddy.model.ChatModel;

import java.util.ArrayList;


public class ChatBotAdpter extends RecyclerView.Adapter {

    ArrayList<ChatModel> arrayList;
    Context context;

    final int MESSAGE_SEND = 1;

    final int MESSAGE_RECIVED = 2;

    public ChatBotAdpter(ArrayList<ChatModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case MESSAGE_SEND:
                View view = LayoutInflater.from(context).inflate(R.layout.sender_message_layout,parent,false);
                return new SenderMessageViewHolder(view);

            case MESSAGE_RECIVED:
                View view2 = LayoutInflater.from(context).inflate(R.layout.chatbot_reply_layout,parent,false);
                return new ChatBotViewHolder(view2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == MESSAGE_SEND)
        {
            ((SenderMessageViewHolder) holder).senderMessage.setText(arrayList.get(position).getMessage());
        }
        else
        {
            ((ChatBotViewHolder) holder).botMessage.setText(arrayList.get(position).getMessage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getViewType() == 1)
        {
            return MESSAGE_SEND;
        }
        else
        {
            return MESSAGE_RECIVED;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SenderMessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderMessage;
        public SenderMessageViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.user_message_send);
        }
    }

    class ChatBotViewHolder extends RecyclerView.ViewHolder {
        TextView botMessage;
        public ChatBotViewHolder(@NonNull View itemView) {
            super(itemView);

            botMessage = itemView.findViewById(R.id.chat_bot_reply);

        }
    }

}
