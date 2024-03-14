package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.databinding.ActivityProfileBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)    //툴바 사용 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)    //왼쪽 버튼 사용설정(기본은 뒤로가기)

        supportActionBar!!.setDisplayShowTitleEnabled(true)        //타이틀 보이게 설정


        binding.tvUserId.text = MySharedpreferences.getUserNick(applicationContext)

        val fileName = MySharedpreferences.getFileUrl(this)

        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference("image")

        if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
            // 조건이 충족되는 경우의 처리 로직
            val pathReference = storageReference.child(fileName)
            pathReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(binding.ivUser);
            }.addOnFailureListener {
                Log.d("image", "가져오기 실패")
            }

        } else {

            Log.d("image", "데이터 없음")
        }



//       뒤로 가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        평가 페이지 이동
        binding.btnPyeongga.setOnClickListener {
            val intent = Intent(this, PuynggaActivity::class.java)
            startActivity(intent)
        }

        // 프로필에서 판매로 넘어 가는 버튼
        binding.btnPanmea1.setOnClickListener {
            val intent = Intent(this, PanmaeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGumae1.setOnClickListener {
            val intent = Intent(this, GumaeActivity::class.java)
            startActivity(intent)
        }

        binding.btnDeill.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        //       프로핗 수정 페이지
        binding.btnGallery.setOnClickListener {
            val intent = Intent(this, profilesojoungActivity::class.java)
            startActivity(intent)


        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainaction, menu)		//작성한 메뉴파일 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId){
            android.R.id.home->{	//각 버튼 마다 스낵바 메세지로 기능 구현
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_search->{

            }

        }

        return super.onOptionsItemSelected(item)
    }


    //액션버튼 메뉴 액션바에 집어 넣기



}