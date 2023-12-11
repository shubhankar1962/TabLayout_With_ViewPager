package com.example.chatapppractice.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatapppractice.MainActivity
import com.example.chatapppractice.R
import com.example.chatapppractice.databinding.ActivityLoginActitvityBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActitvity : AppCompatActivity() {

    lateinit var binding:ActivityLoginActitvityBinding

    lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActitvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        mauth = FirebaseAuth.getInstance()

        binding.registerTv.setOnClickListener{
            startActivity(Intent(this, SignUp::class.java))
        }

        binding.loginBtn.setOnClickListener{
            val mail = binding.loginmail.text.toString()
            val password = binding.loginpassword.text.toString()

            Login(mail,password)
        }
    }

    fun Login(mail:String, password:String)
    {
        mauth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this){task->
            if(task.isSuccessful)
            {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}