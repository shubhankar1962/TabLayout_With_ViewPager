package com.example.chatapppractice.tabFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.chatapppractice.R
import com.example.chatapppractice.adapter.UserAdapter
import com.example.chatapppractice.databinding.FragmentChatBinding
import com.example.chatapppractice.users.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ChatFragment : Fragment() {
    private lateinit var userList:ArrayList<User>

    private lateinit var adapter: UserAdapter
    private lateinit var dbinstance:DatabaseReference
    lateinit var binding: FragmentChatBinding
    private lateinit var mauth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dbinstance = FirebaseDatabase.getInstance().getReference()
        userList = ArrayList()
        adapter = UserAdapter(requireActivity(), userList)
        mauth = FirebaseAuth.getInstance()

        binding.userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.userRecyclerView.adapter = adapter

        dbinstance.child("users").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                //userList.clear() //this is why we added another user then it will clear the first list and then again iterate from start
                for(postsnapShot in snapshot.children)
                {
                    val currenntUser = postsnapShot.getValue(User::class.java)


                    //checking user itself is not not visible in the list
                    if(mauth.currentUser?.uid != currenntUser?.uid)
                    {
                        currenntUser?.let{userList.add(it)}
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}