package com.bitc.plumMarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Adapter.ListAdapter
import com.bitc.plumMarket.Data.ChatData
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityListRecyclerViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = ActivityListRecyclerViewBinding.inflate(inflater, container, false)

        // RecyclerView 초기화
        val recyclerView: RecyclerView = binding.recyclerViewList

        // LinearLayoutManager 설정
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        // DividerItemDecoration 설정
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)

        // RecyclerView에 Adapter 설정 등의 작업 수행

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeData()
    }

    override fun onResume() {
        super.onResume()
        setupHomeData()
    }

    fun setupHomeData(){
        RetrofitBuilder.api.getListData().enqueue(object: Callback<List<ListData>> {
            override fun onResponse(call: Call<List<ListData>>, response: Response<List<ListData>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    val items = mutableListOf<ListData>()
                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {
                            val title = data.list_title
                            val money = data.list_money
                            val idx = data.list_idx
                            val image = data.list_image_name

                            items.add(ListData(idx,title,money, image))
                            val listAdapter = ListAdapter(items)
                            // 변수에 값을 넣어 사용하거나 처리해주세요
                            // 예시: 로그로 출력

                            val linearLayoutManager = LinearLayoutManager(requireContext())
                            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

                            // Fragment의 레이아웃에서 리사이클러뷰를 찾아서 설정
                            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewList)
                            recyclerView?.layoutManager = linearLayoutManager
                            recyclerView?.adapter = listAdapter
                            recyclerView?.addItemDecoration(
                                DividerItemDecoration(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL
                                )
                            )
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