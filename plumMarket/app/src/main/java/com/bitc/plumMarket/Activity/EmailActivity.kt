package com.bitc.plumMarket.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.databinding.ActivityEmailBinding


class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val binding = ActivityEmailBinding.inflate(layoutInflater)
         setContentView(binding.root)

        }
    }
