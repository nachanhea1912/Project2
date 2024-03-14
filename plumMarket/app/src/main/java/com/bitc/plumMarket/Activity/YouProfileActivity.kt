package com.bitc.plumMarket.Activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.Data.UserDTO
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityYouProfileBinding
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityYouProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)   //툴바 사용 설정

        val nickname = intent.getStringExtra("nick").toString()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)   //왼쪽 버튼 사용설정(기본은 뒤로가기)

        supportActionBar!!.setDisplayShowTitleEnabled(true)      //타이틀 보이게 설정

        RetrofitBuilder.api.userInfo(nickname).enqueue(object: Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                if(response.isSuccessful) {

                    val nick = response.body()?.userNick.toString()
                    val comment = response.body()?.userComment.toString()
                    val rating = response.body()?.userRating.toString()
                    MySharedpreferences.setUserRating(this@YouProfileActivity, rating)
                    val profile = response.body()?.userProfile.toString()
                    val list = response.body()?.listCount.toString()
                    Log.d("eee", "${nick}")
                    binding.tvUserId.text = nick
                    binding.comment.text = comment
                    binding.gaugeLabel.text = rating
                    binding.listCount.text = "${list}개"
                    val barChart = binding.chart2
                    binding.chart2.setTouchEnabled(false)




                    val entries = mutableListOf<BarEntry>()
                    entries.add(BarEntry(0f, rating.toFloat()))

                    val dataSet = BarDataSet(entries, "매너온도")
                    dataSet.color = Color.GREEN

                    val barData = BarData(dataSet)
                    barData.barWidth = 0.5.toFloat()

                    barChart.data = barData
                    barChart.setFitBars(true)
                    barChart.description.isEnabled = false
                    barChart.legend.isEnabled = false

                    val xAxis: XAxis = barChart.xAxis
                    xAxis.isEnabled = false

                    val leftAxis: YAxis = barChart.axisLeft
                    leftAxis.axisMinimum = 0f
                    leftAxis.axisMaximum = 100f
                    leftAxis.setDrawAxisLine(false)
                    leftAxis.setDrawGridLines(false)

                    val rightAxis: YAxis = barChart.axisRight
                    rightAxis.isEnabled = false

                    barChart.invalidate()

                    val fileName = profile.toString()

                    val storage = FirebaseStorage.getInstance()
                    val storageReference = storage.getReference("image")

                    if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
                        // 조건이 충족되는 경우의 처리 로직
                        val pathReference = storageReference.child(fileName)
                        pathReference.downloadUrl.addOnSuccessListener { uri ->
                            Glide.with(this@YouProfileActivity).load(uri).into(binding.ivUser);
                        }.addOnFailureListener {
                            Log.d("image", "가져오기 실패")
                        }

                    } else {

                        Log.d("image", "데이터 없음")
                    }



                }



            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }
        })




        binding.btnMan.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("매너평점")
                .setMessage("평점 선택")
                .setPositiveButton("평점 플러스",
                    DialogInterface.OnClickListener { dialog, id ->
                        RetrofitBuilder.api.PlusRating(nickname).enqueue(object: Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {


                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d("error", t.localizedMessage)
                            }
                        })

                        finish()
                        startActivity(intent)


                    })
                .setNegativeButton("평점 마이너스",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "sadas", Toast.LENGTH_LONG).show()
                        RetrofitBuilder.api.MinusRating(nickname).enqueue(object: Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {


                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d("error", t.localizedMessage)
                            }
                        })
                        finish()
                        startActivity(intent)
                    })
            // 다이얼로그를 띄워주기
            builder.show()
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

            }

        }

        return super.onOptionsItemSelected(item)
    }
}