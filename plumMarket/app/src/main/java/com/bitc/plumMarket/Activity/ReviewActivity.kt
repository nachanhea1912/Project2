package com.bitc.plumMarket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.bitc.plumMarket.Adapter.ReviewAdapter
import com.bitc.plumMarket.Fragment.ReviewFragment1
import com.bitc.plumMarket.Fragment.ReviewFragment2
import com.bitc.plumMarket.Fragment.ReviewFragment3
import com.bitc.plumMarket.databinding.ActivityReviewBinding

import com.google.android.material.tabs.TabLayoutMediator

class ReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var reviewAdapter = ReviewAdapter(this)
        reviewAdapter.addFragment(ReviewFragment1())
        reviewAdapter.addFragment(ReviewFragment2())
        reviewAdapter.addFragment(ReviewFragment3())

        //Adapter 연결
        binding.ReviewPager2.apply {
            adapter = reviewAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tlNavigationView1, binding.ReviewPager2) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "전체 후기"
                1 -> tab.text = "판매자 후기"
                2 -> tab.text = "구매자 후기"
            }
        }.attach()
        //             뒤로가기 버튼 사용 마이페이지 이동
        binding.btnBack.setOnClickListener {
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}


