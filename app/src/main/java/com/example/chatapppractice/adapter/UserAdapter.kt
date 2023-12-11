package com.example.chatapppractice.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapppractice.R
import com.example.chatapppractice.chat.ChatActivity
import com.example.chatapppractice.databinding.UserLayoutBinding
import com.example.chatapppractice.users.User
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context:Context, val userList:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(R.id.user_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //val binding = UserLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser = userList[position]
        holder.name.text = currentUser.name

        holder.itemView.setOnClickListener{
            val intent  = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            context.startActivity(intent)
        }
//
//            //val binding = UserLayoutBinding.bind(holder.itemView)
       // holder.binding.userNameTv.text = currentUser.name
    }

}