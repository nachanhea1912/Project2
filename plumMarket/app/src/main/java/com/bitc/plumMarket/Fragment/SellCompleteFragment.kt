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


class SellCompleteFragment : Fragment(), OnSellCompleteListener {

    private lateinit var listSellAdapter: ListSellAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ActivityListRecyclerViewBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.recyclerViewList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        listSellAdapter = ListSellAdapter(mutableListOf(), activity as AppCompatActivity, recyclerView, object : OnSellCompleteListener {
            override fun onSellComplete() {
                // SellCompleteFragment에서 처리할 작업
                setupSellCompleteViewData()
            }
        },MySharedpreferences.getPosition(requireContext()))
        recyclerView.adapter = listSellAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSellCompleteViewData()
    }

    override fun onResume() {
        super.onResume()

        setupSellCompleteViewData()
    }


    private fun setupSellCompleteViewData() {
        val nick = MySharedpreferences.getUserNick(requireContext())

        RetrofitBuilder.api.selectPanmaeCompleteList(nick)
            .enqueue(object : Callback<List<ListData>> {
                override fun onResponse(
                    call: Call<List<ListData>>,
                    response: Response<List<ListData>>
                ) {
                    if (response.isSuccessful) {
                        val list = response.body()
                        val items = list.orEmpty().toMutableList()

                        listSellAdapter.setItems(items)
                    } else {
                        Log.d("ysh", "listData is null")
                    }
                }


                override fun onFailure(call: Call<List<ListData>>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
    }

    override fun onSellComplete() {
        setupSellCompleteViewData()
    }
}