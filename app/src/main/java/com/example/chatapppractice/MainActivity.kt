package com.example.chatapppractice

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapppractice.adapter.ViewPagerAdapter
import com.example.chatapppractice.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
}