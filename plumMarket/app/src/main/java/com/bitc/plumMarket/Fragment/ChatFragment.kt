package com.bitc.plumMarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitc.plumMarket.Adapter.UserAdapter
import com.bitc.plumMarket.Data.ChatList
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityUserListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatFragment : Fragment() {
    lateinit var binding: ActivityUserListBinding
    lateinit var  adapter: UserAdapter
    private lateinit var userList : ArrayList<ChatList>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)







        val idx = MySharedpreferences.getUserIdx(requireContext())
        RetrofitBuilder.api.getChatList(idx).enqueue(object: Callback<List<ChatList>> {
            override fun onResponse(call: Call<List<ChatList>>, response: Response<List<ChatList>>) {
                if(response.isSuccessful){
                    val list = response.body()
                    if (list != null) {
                        userList = ArrayList()
                        adapter = UserAdapter(requireContext(),userList)
                        binding.userRecycelrView.layoutManager = LinearLayoutManager(requireContext())
                        binding.userRecycelrView.adapter = adapter

                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {

                            var idx= data.user_idx
                            var nick = data.user_nick
                            var send_idx = data.send_idx
                            var receive_idx =data.receive_idx
                            var list_uid = data.listUid

                            val index = list.indexOf(data)

                            userList.add(index, ChatList(idx,nick,send_idx,receive_idx,list_uid))

                            // 변수에 값을 넣어 사용하거나 처리해주세요
                            // 예시: 로그로 출력

                        }




                    } else {
                        Log.d("ysh", "listData is null")
                    }
                }
            }

            override fun onFailure(call: Call<List<ChatList>>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }
        })


    }
}