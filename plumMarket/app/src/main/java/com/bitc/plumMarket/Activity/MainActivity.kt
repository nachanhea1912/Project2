package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI


import com.bitc.plumMarket.R
import com.bitc.plumMarket.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)	//툴바 사용 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)	//왼쪽 버튼 사용설정(기본은 뒤로가기)

        supportActionBar!!.setDisplayShowTitleEnabled(true)			//타이틀 보이게 설정

        // Find the NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        // Set up the BottomNavigationView with the NavController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainaction, menu)      //작성한 메뉴파일 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId){
            android.R.id.home->{   //각 버튼 마다 스낵바 메세지로 기능 구현
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_search->{
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item_id->{
                val intent = Intent(this,WriteActivity::class.java)
                startActivity(intent)
            }


        }

        return super.onOptionsItemSelected(item)
    }



}