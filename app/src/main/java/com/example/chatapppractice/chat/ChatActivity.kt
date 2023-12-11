package com.example.chatapppractice.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapppractice.R
import com.example.chatapppractice.adapter.MessageAdapter
import com.example.chatapppractice.databinding.ActivityChatBinding
import com.example.chatapppractice.databinding.UserLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageList:ArrayList<Message>
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var dbinstance:DatabaseReference
    var recieveRoom:String? = null
    var senderRoom:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbinstance = FirebaseDatabase.getInstance().getReference()
        messageList = ArrayList()
        messageAdapter  = MessageAdapter(this, messageList)


        val name = intent.getStringExtra("name")
        val recieverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        supportActionBar?.title = name

        senderRoom = recieverUid+senderUid
        recieveRoom = senderUid+recieverUid


        //attaching the recyclerview to adapter
        binding.ChatRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.ChatRecyclerView.adapter = messageAdapter

        //logic for adding messages to database

        dbinstance.child("chats").child(recieveRoom!!).child("messages").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                messageList.clear()
                for(postSnapshot in snapshot.children)
                {
                    val message = postSnapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                }
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        } )
        dbinstance.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()
                    for(postSnapshot in snapshot.children){

                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        //storing the data to the database on click of send btn
        binding.sendMessageImg.setOnClickListener{

            val message = binding.sendMessageEt.text.toString()
            val messageObject = Message(message, senderUid!!)

            dbinstance.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {

                    dbinstance.child("chats").child(recieveRoom!!).child("messages").push()
                        .setValue(messageObject)

                }
            binding.sendMessageEt.setText("")
        }

    }
}