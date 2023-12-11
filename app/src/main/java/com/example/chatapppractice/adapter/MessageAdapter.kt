package com.example.chatapppractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapppractice.R
import com.example.chatapppractice.chat.Message
import com.example.chatapppractice.databinding.RecieveChatBinding
import com.example.chatapppractice.databinding.SendChatBinding
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIEVE =1
    val ITEM_SENT =2

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId))
        {
            return ITEM_SENT
        }else{
            return ITEM_RECIEVE
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType ==1)
        {
            //inflating the recieve chat
            val view:View = LayoutInflater.from(context).inflate(R.layout.recieve_chat,parent,false)
            return RecieveMessageViewHolder(view)
        }
        else{
            //inflating the sent chat
            val view:View = LayoutInflater.from(context).inflate(R.layout.send_chat,parent,false)
            return SendMessageViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMessage = messageList[position]
        if(holder.javaClass == SendMessageViewHolder::class.java)
        {
            //work for sendviewholder
            val viewHolder = holder as SendMessageViewHolder
            holder.sentMessage.text = currentMessage.message


        }else{
            //do the task for recieveViewholder
            val viewHolder = holder as RecieveMessageViewHolder
            holder.recieveMessage.text = currentMessage.message

        }
    }


    class SendMessageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val  sentMessage = itemView.findViewById<TextView>(R.id.sendChatTv)
    }


     class RecieveMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val recieveMessage = itemView.findViewById<TextView>(R.id.recieveChatTv)
    }




}