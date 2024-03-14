package com.bitc.plumMarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BottomSheetFragment(
    private val idx: String,
    val tvSellReservation: TextView,
    private val sellCompleteListener: OnSellCompleteListener
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postion = MySharedpreferences.getPosition(requireContext())
        val res = binding.tvSellReservation
        val comp = binding.tvSellComplete
        val hide = binding.tvSellHide
        val sell = binding.tvSellOngoing

        fun sellStateCheck(idx: String, callback: (String) -> Unit) {
            RetrofitBuilder.api.selectSellState(idx).enqueue(object : Callback<ListData> {
                override fun onResponse(call: Call<ListData>, response: Response<ListData>) {
                    if (response.isSuccessful) {
                        val sellState = response.body()?.list_sell_state.toString()
                        callback(sellState)
                    } else {
                        // 실패 시에 대한 처리
                        Log.e("sellStateCheck", "요청 실패: ${response.code()}")
                        callback("")
                    }
                }

                override fun onFailure(call: Call<ListData>, t: Throwable) {
                    Log.e("sellStateCheck", "에러 발생: ${t.localizedMessage}")
                    callback("")
                }
            })
        }

        when(postion){
            "0" -> {
                sellStateCheck(idx) { sellState ->
                    Log.d("sellState", "${sellState}뷁")
                    when (sellState) {
                        "3" -> {
                            res.visibility = View.GONE
                            sell.visibility = View.VISIBLE
                        }
                        else -> {
                            res.visibility = View.VISIBLE
                            comp.visibility = View.VISIBLE
                        }
                    }
                }
            }
            "1" -> {
                sell.visibility = View.VISIBLE
            }
            "2" -> {
                hide.visibility = View.GONE
            }
        }





        binding.tvSellOngoing.setOnClickListener{
            RetrofitBuilder.api.updateSellOngoing(idx).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        tvSellReservation.visibility = View.GONE
                        dismiss()
                        // SellOngoingFragment 리로딩을 위해 콜백 호출
                        sellCompleteListener.onSellComplete()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
        }

        binding.tvSellReservation.setOnClickListener {

            if(tvSellReservation.visibility == View.GONE){
                RetrofitBuilder.api.updateSellRervation(idx).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            tvSellReservation.visibility = View.VISIBLE
                            dismiss()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }
                })
            }
            else{
                RetrofitBuilder.api.updateSellRervationDelete(idx).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            tvSellReservation.visibility = View.GONE
                            dismiss()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }
                })
            }


            dismiss()
        }

        binding.tvSellComplete.setOnClickListener {
            RetrofitBuilder.api.updateSellComplete(idx).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        dismiss()
                        // SellOngoingFragment 리로딩을 위해 콜백 호출
                        sellCompleteListener.onSellComplete()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
        }

        binding.tvSellEdit.setOnClickListener {

        }

        binding.tvSellHide.setOnClickListener {
            RetrofitBuilder.api.updateSellHide(idx).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        dismiss()
                        sellCompleteListener.onSellComplete()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
        }

        binding.tvSellDelete.setOnClickListener {
            RetrofitBuilder.api.updateSellDelete(idx).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        dismiss()
                        sellCompleteListener.onSellComplete()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
        }
    }



}