package com.example.chatapppractice

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapppractice.adapter.UserAdapter
import com.example.chatapppractice.adapter.ViewPagerAdapter
import com.example.chatapppractice.databinding.ActivityMainBinding
import com.example.chatapppractice.users.User
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var userList : ArrayList<User>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mauth = FirebaseAuth.getInstance()
        userList = ArrayList()
        adapter = UserAdapter(this,userList)



        binding.tabs.addTab(binding.tabs.newTab().setText("Chats"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Status"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Calls"))

        binding.viewPagerId.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)



        TabLayoutMediator(binding.tabs,binding.viewPagerId) { tab,position ->
                tab.text = when(position)
                {
                    0-> {"Chat"}
                    1-> {"Status"}
                    2-> {"Calls"}
                    else-> {throw Resources.NotFoundException("position not found")}
                }

        }.attach()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout)
        {
            mauth.signOut()
            finish()
            return true
        }
        return true
    }
}