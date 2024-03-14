package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.databinding.ActivityMypageBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val storage = FirebaseStorage.getInstance()
        val fileName = MySharedpreferences.getFileUrl(this)
        val storageReference = storage.getReference("image")


        if (fileName != null) {

            val pathReference = storageReference.child(fileName)
            pathReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(binding.profilePicture);
            }.addOnFailureListener {
                Log.d("image", "가져오기 실패")
            }

        } else {

            Log.d("image", "데이터 없음")
        }

//        관심 버튼
        binding.btnGansim.setOnClickListener {
            intent = Intent(this, GansimActivity::class.java)
            startActivity(intent)
        }

//         판매 버튼
        binding.btnPanmae.setOnClickListener {
            intent = Intent(this, PanmaeActivity::class.java)
            startActivity(intent)
        }

//        구매 버튼
        binding.btnGumae.setOnClickListener {
            intent = Intent(this, GumaeActivity::class.java)
            startActivity(intent)
        }

        binding.profilePicture.setOnClickListener {
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.btnMypageProfile.setOnClickListener {
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.logoutButton.setOnClickListener(){
            MySharedpreferences.clearUser(applicationContext)
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}