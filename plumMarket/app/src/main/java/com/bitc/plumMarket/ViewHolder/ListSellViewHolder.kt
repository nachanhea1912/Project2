package com.bitc.plumMarket.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.databinding.ListSellItemBinding


// 리사이클러 뷰의 개별 항목 xml 파일을 파싱하는 ViewHolder 파일
// 매개변수로 리사이클러 뷰의 개별 항목이 되는 xml 파일을 받아옴
// 주 생성자의 매개변수에 val 키워드를 추가하여 해당 클래스의 필드로 선언
// 원래는 findViewById() 함수로 xml 파일이 가지고 있는 모든 태그를 가져와서 부모 클래스의 생성자에 전달해야 하지만 view binding 기법을 사용하면 쉽게 전달할 수 있음
class ListSellViewHolder(val binding: ListSellItemBinding): RecyclerView.ViewHolder(binding.root)









