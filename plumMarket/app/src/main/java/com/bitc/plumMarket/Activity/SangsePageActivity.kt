package com.bitc.plumMarket.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bitc.plumMarket.Adapter.SlideAdapter
import com.bitc.plumMarket.Adapter.imageAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivitySangsePageBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SangsePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivitySangsePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var idx= intent.getIntExtra("selected_idx", -1).toString()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)	//툴바 사용 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)	//왼쪽 버튼 사용설정(기본은 뒤로가기)

        supportActionBar!!.setDisplayShowTitleEnabled(true)			//타이틀 보이게 설정








        val imageUriList = mutableListOf<String>()

        var reIdx : String =  ""
        var nickName : String = ""
        var userIdx : String = ""




//    뷰 페이저2에 사용할 프래그먼트 리스트 생성


        //    뷰 페이저2 어뎁터에 사용할 프래그먼트 리스트 등록


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
                            userIdx = data.user_idx.toString()

                            nickName = data.list_user_nick.toString()


                            binding.TvTitle.text = title.toString()
                            binding.comment.text = content.toString()
                            binding.tvMoney.text = "${money.toString()} 원"
                            binding.tvUserId.text = nick.toString()
                            binding.viewCount.text = viewCount.toString()
                            binding.tvLoc.text = loc.toString()


                            // 이미지 변수를 imageUriList에 추가
                            imageUriList.add(image)
                            reIdx = data.list_idx.toString()
                            MySharedpreferences.setProfile(this@SangsePageActivity,data.user_profile.toString())


                        }

                        // 이미지 추가 후 뷰페이저 어댑터에 전달
                        val viewPager = binding.viewPagerSlide
                        viewPager.adapter = SlideAdapter(this@SangsePageActivity, imageUriList)
                        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


                        if(nickName.equals(MySharedpreferences.getUserNick(this@SangsePageActivity))){

                            binding.btnChatting.text = "글 수정"
                            binding.btnChatting.setOnClickListener{
                                val intent = Intent(this@SangsePageActivity, GesigeulPageActivity::class.java)
                                intent.putExtra("reIdx", reIdx) // 값을 넘기고 싶은 경우, "key"와 value를 원하는 값으로 대체해주세요
                                startActivity(intent)
                            }
                        }else{
                            binding.btnChatting.setOnClickListener{

                                intent = Intent(this@SangsePageActivity, ChatActivity::class.java)
                                intent.putExtra("listUid", reIdx)
                                intent.putExtra("nick", nickName)
                                intent.putExtra("uId", userIdx)
                                startActivity(intent)
                            }

                        }

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


        val viewPager = binding.viewPagerSlide
        viewPager.adapter = SlideAdapter(this, imageUriList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference("image")

        val fileName = MySharedpreferences.getProfile(this)

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


        binding.ivUser.setOnClickListener{
            intent = Intent(this@SangsePageActivity, YouProfileActivity::class.java)
            intent.putExtra("nick", nickName)
            startActivity(intent)
        }

        binding.btnFav.setOnClickListener {


            RetrofitBuilder.api.insertGansim(MySharedpreferences.getUserId(this),idx).enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                ) {
                    if (response.isSuccessful) {
                        var result = response.body()
                        if(result == 1){
                            Toast.makeText(this@SangsePageActivity,"이미 관심목록에 저장되었습니다.", Toast.LENGTH_SHORT).show()
                        }else{
                            binding.btnFav.setBackgroundColor(this@SangsePageActivity.getResources().getColor(R.color.pink));
                            Toast.makeText(this@SangsePageActivity,"관심목록에 저장되었습니다.", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Log.d("ysh", "listData is null")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }


            })

        }





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