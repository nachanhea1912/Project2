package com.bitc.plumMarket.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Adapter.ListAdapter
import com.bitc.plumMarket.Adapter.ListSellAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.Fragment.OnSellCompleteListener
import com.bitc.plumMarket.RetrofitBuilder

import com.bitc.plumMarket.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val Search =  binding.searchBadge.text.toString()

            RetrofitBuilder.api.SearchListTitle(Search).enqueue(object : Callback<List<ListData>>{
                override fun onResponse(
                    call: Call<List<ListData>>,
                    response: Response<List<ListData>>,
                ) {
                    if(response.isSuccessful){
                        val list = response.body()
                        val items = mutableListOf<ListData>()

                        if (list != null) {
                            // listData를 활용하여 필요한 처리를 수행해주세요
                            // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                            for (data in list) {
                                val idx = data.list_idx
                                val title = data.list_title
                                val money = data.list_money
                                val image = "22"

                                items.add(ListData(idx,title,money, image))
                                val listAdapter = ListAdapter(items)
                                // 변수에 값을 넣어 사용하거나 처리해주세요
                                // 예시: 로그로 출력

                                binding.RecyclerList.itemAnimator = null
                                binding.RecyclerList.layoutManager = LinearLayoutManager(this@SearchActivity)
                                binding.RecyclerList.adapter = listAdapter

                            }
                        } else {
                            Log.d("ysh", "listData is null")
                        }
                    }
                }

                override fun onFailure(call: Call<List<ListData>>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }

            })





        }
    }
}




