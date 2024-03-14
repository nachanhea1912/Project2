package com.bitc.plumMarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Adapter.ListSellAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityListRecyclerViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellOngoingFragment : Fragment(), OnSellCompleteListener {

    private lateinit var listSellAdapter: ListSellAdapter
    private lateinit var binding: ActivityListRecyclerViewBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityListRecyclerViewBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        listSellAdapter = ListSellAdapter(mutableListOf(), activity as AppCompatActivity, recyclerView, object : OnSellCompleteListener {
            override fun onSellComplete() {
                // SellCompleteFragment에서 처리할 작업
                setupRecyclerViewData()
            }
        },MySharedpreferences.getPosition(requireContext()))

        recyclerView.adapter = listSellAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewData()
    }

    override fun onResume() {
        super.onResume()

        setupRecyclerViewData()
    }

    private fun setupRecyclerViewData() {
        val nick = MySharedpreferences.getUserNick(requireContext())

        RetrofitBuilder.api.selectPanmaeList(nick).enqueue(object : Callback<List<ListData>> {
            override fun onResponse(call: Call<List<ListData>>, response: Response<List<ListData>>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    val items = list.orEmpty().toMutableList()

                    listSellAdapter.setItems(items)
                } else {
                    Log.d("ysh", "Failed to fetch data")
                }
            }

            override fun onFailure(call: Call<List<ListData>>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }
        })

    }

    override fun onSellComplete() {
        // 여기서 SellOngoingFragment의 데이터를 다시로드하거나 새로 고칩니다.
        setupRecyclerViewData()
    }

}