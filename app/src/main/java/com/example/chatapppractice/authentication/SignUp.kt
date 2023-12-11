package com.example.chatapppractice.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatapppractice.MainActivity
import com.example.chatapppractice.databinding.ActivitySignUpBinding
import com.example.chatapppractice.users.User

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var mauth:FirebaseAuth
    private lateinit var dbinstance:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        mauth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener{

            val mail =  binding.signUpmail.text.toString().trim()
            val name = binding.etName.text.toString()
            val password = binding.signUppassword.text.toString()
                //val cpassword = binding.cnfmPassword.text.toString()

            register(name,mail, password)
        }

    }

    private fun register(name:String,mail:String, password:String)
    {
        mauth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(this@SignUp){task ->
            if(task.isSuccessful)
            {

                addUserToDB(name, mail, password,mauth.uid!!)
                Log.d("Tag", "Signup successful with email")
                startActivity(Intent(this, MainActivity::class.java))

            }else{
                Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addUserToDB(name:String,mail: String, password: String,uid:String)
    {
        dbinstance = FirebaseDatabase.getInstance().getReference()
        dbinstance.child("users").child(uid).setValue(User(name,mail,uid))
    }
}