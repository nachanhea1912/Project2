package com.bitc.plumMarket.Adapter


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter


class imageAdapter(fa: FragmentActivity, private val imageUriList: List<String>): FragmentStateAdapter(fa) {
    var fragmentList = listOf<Fragment>()

    //  등록된 프래그먼트의 수 출력
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    //  프래그먼트 리스트의 수 만큼 뷰 페이저에서 사용할 프래그먼트를 생성
    override fun createFragment(position: Int): Fragment {
        val fragment = fragmentList[position]

        for(list in imageUriList){
            Log.d("listff", "${list}")
        }


        return fragment
    }
}