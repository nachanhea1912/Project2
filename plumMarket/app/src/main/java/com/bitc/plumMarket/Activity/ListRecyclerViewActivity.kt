package com.bitc.plumMarket.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitc.plumMarket.Adapter.ListAdapter


import com.bitc.plumMarket.Data.ChatData
import com.bitc.plumMarket.databinding.ActivityListRecyclerViewBinding

class ListRecyclerViewActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityListRecyclerViewBinding.inflate(layoutInflater)
    setContentView(binding.root)



  }
}
