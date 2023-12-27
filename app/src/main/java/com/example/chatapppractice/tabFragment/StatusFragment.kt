package com.example.chatapppractice.tabFragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chatapppractice.R
import com.example.chatapppractice.databinding.FragmentStatusBinding

class StatusFragment : Fragment() {

    lateinit var binding:FragmentStatusBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addStatusProfile.setOnClickListener{
            openGallery()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == 1)
            {
                val list = it.data
            }
        }
    }

}