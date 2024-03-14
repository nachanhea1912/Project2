package com.bitc.plumMarket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.bitc.plumMarket.Adapter.SlideAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences

import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityGesigeulPageBinding
import com.bitc.plumMarket.databinding.ActivityWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GesigeulPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val idx = intent.getStringExtra("reIdx").toString()
        super.onCreate(savedInstanceState)
        val binding = ActivityGesigeulPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 값이 입력되었는지 여부를 확인
        val imageUriList = mutableListOf<String>()
        RetrofitBuilder.api.DetailSelect(idx).enqueue(object : Callback<List<ListData>> {
            override fun onResponse(call: Call<List<ListData>>, response: Response<List<ListData>>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    val items = mutableListOf<ListData>()

                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {

                            val title = data.list_title
                            val money = data.list_money
                            val loc = data.list_loc
                            val content = data.list_content
                            val nick = data.list_user_nick
                            val image = data.list_image_name
                            val viewCount = data.list_hit_cnt






                            // 이미지 변수를 imageUriList에 추가
                            // 이미지 변수를 imageUriList에 추가
                            imageUriList.add(image)


                            binding.edMoney.hint = money.toString()
                            binding.edTitle.hint = title.toString()
                            binding.edcontent.hint = content.toString()
                            binding.tvNickId.text = nick.toString()

                        }


                        // 이미지 추가 후 뷰페이저 어댑터에 전달
                        val viewPager = binding.viewPagerSlide
                        viewPager.adapter = SlideAdapter(this@GesigeulPageActivity, imageUriList)
                        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL






                    } else {
                        Log.d("ysh", "listData is null")
                    }
                } else {
                    Log.d("ysh", "Server response unsuccessful. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ListData>>, t: Throwable) {
                Log.e("error", "Network request failed", t)
            }
        })

        // 'btnBack', 'btnHome', 'btnSujoungWan' 버튼의 클릭 리스너 등록
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Intent에서 'idx_from_bottom_sheet' 키로 전달된 값 읽어오기
//        val receivedIdx = intent.getStringExtra("idx_from_bottom_sheet")
//        val idx = this.intent.getStringExtra("idx_from_bottom_sheet")
//        if (idx != null) {
//            // 'receivedIdx' 값을 필요한 대로 사용
//            Log.d("ss", "받은 idx: $idx")


        // 값이 null이 아닌지 확인 후 사용
        binding.btnSujoungWan.setOnClickListener {

            val title = binding.edTitle.text.toString()
            val money = binding.edMoney.text.toString()
            val content = binding.edcontent.text.toString()
//




            // Retrofit을 사용하여 서버에 데이터를 등록
//             Void는 db연동하는 부분에서 "WriteController" 에서 void 와 똑같이 있는데 수정페이지는 반환값이 없어도 되어 void를 함.
            RetrofitBuilder.api.modifyList(idx,title, money.toInt(), content)
                .enqueue(object : Callback<Void> {

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {

                        Toast.makeText(this@GesigeulPageActivity, "수정완료되었습니다", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@GesigeulPageActivity, MainActivity::class.java)
                        startActivity(intent)


                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }
                })
//
            if (title.isNotBlank() && money.isNotBlank() && content.isNotBlank()) {
                val intent = Intent(this, PanmaeActivity::class.java)
                startActivity(intent)
                // 'receivedIdx' 값을 필요한 대로 사용
                // Log.d("GesigeulPageActivity", "받은 idx: $receivedIdx")
                // 'receivedIdx'를 사용하여 데이터를 등록할 수 있는 부분
            } else {
                Toast.makeText(this, "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            }
//                val intent = Intent(this, SangsePageActivity::class.java)
//                startActivity(intent)
        }
//        } else {
//            Log.d("ss", "받은 idx가 없습니다.")
//            // idx가 없으면 필요한 처리를 여기에 추가
//            // 예: 사용자에게 알림을 표시하거나, 다른 화면으로 이동
//        }

    }

}


