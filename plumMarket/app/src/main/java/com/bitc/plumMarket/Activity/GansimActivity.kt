package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitc.plumMarket.Adapter.GansimAdapter

import com.bitc.plumMarket.Data.GansimData
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityGansimBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GansimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGansimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = MySharedpreferences.getUserId(applicationContext)

        RetrofitBuilder.api.GansimList(userId).enqueue(object : Callback<List<GansimData>> {
            override fun onResponse(call: Call<List<GansimData>>, response: Response<List<GansimData>>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    val items = mutableListOf<GansimData>()
                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {
                            val idx = data.fav_list_idx
                            val title = data.fav_title
                            val money = data.fav_money
                            val image= data.fav_image
                            val sellState= data.fav_sell_state

                            items.add(GansimData(idx, title, money,image,sellState))
                        }

                        val gansimAdapter = GansimAdapter(items)

                        binding.recyclerView.itemAnimator = null
                        binding.recyclerView.layoutManager = LinearLayoutManager(this@GansimActivity)
                        binding.recyclerView.adapter = gansimAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<GansimData>>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }
        })

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MypageActivity::class.java))
        }
    }
}