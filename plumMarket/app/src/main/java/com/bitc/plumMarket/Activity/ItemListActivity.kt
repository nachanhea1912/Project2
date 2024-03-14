package com.bitc.plumMarket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.Data.LoginData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityItemListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityItemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        RetrofitBuilder.api.getListData().enqueue(object: Callback<List<ListData>> {
            override fun onResponse(call: Call<List<ListData>>, response: Response<List<ListData>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {
                            val title = data.list_title
                            val content = data.list_content
                            val nick = data.list_user_nick

                            // 변수에 값을 넣어 사용하거나 처리해주세요
                            // 예시: 로그로 출력
                            Log.d("ysh", "$title $content $nick")
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