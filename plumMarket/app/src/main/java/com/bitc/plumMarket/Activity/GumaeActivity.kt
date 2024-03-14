package com.bitc.plumMarket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitc.plumMarket.Adapter.GumaeAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.Data.ListData1

import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityGumaeBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GumaeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGumaeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userNick = MySharedpreferences.getUserNick(applicationContext)

        val recyclerView = binding.recyclerGumaeList  // 변경된 부분

        RetrofitBuilder.api.getListGumaeList(userNick).enqueue(object: Callback<List<ListData1>> {
            override fun onResponse(call: Call<List<ListData1>>, response: Response<List<ListData1>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    val items = mutableListOf<ListData1>()
                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {
                            val title = data.list_title
                            val money = data.list_money
                            val idx = data.list_idx
                            val image = data.list_image_name
                            val sellState = data.list_sell_state

                            items.add(ListData1(idx, title, money, image, sellState))
                        }

                        val gumaeAdapter = GumaeAdapter(items, this@GumaeActivity)

                        val linearLayoutManager = LinearLayoutManager(applicationContext)
                        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

                        // 리사이클러뷰에 어댑터 설정
                        recyclerView.layoutManager = linearLayoutManager
                        recyclerView.adapter = gumaeAdapter
                        recyclerView.addItemDecoration(
                            DividerItemDecoration(
                                applicationContext,
                                LinearLayoutManager.VERTICAL
                            )
                        )
                    } else {
                        Log.d("ysh", "listData is null")
                    }
                }
            }

            override fun onFailure(call: Call<List<ListData1>>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }
        })

        binding.btnBack.setOnClickListener {
            intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
    }

}