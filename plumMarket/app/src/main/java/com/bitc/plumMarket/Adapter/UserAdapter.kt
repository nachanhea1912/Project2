package com.bitc.plumMarket.Adapter


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Activity.ChatActivity
import com.bitc.plumMarket.Activity.MainActivity
import com.bitc.plumMarket.Data.ChatList
import com.bitc.plumMarket.Data.LoginData
import com.bitc.plumMarket.Fragment.ChatFragment
import com.bitc.plumMarket.MySharedpreferences

import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import retrofit2.Callback
import retrofit2.Response

class UserAdapter(private val context: Context, private val userList: ArrayList<ChatList>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private lateinit var receiverRoom: String //받는 대화방
    private lateinit var senderRoom: String //보낸 대화방
    val database = Firebase.database("https://androidchat-c4b8a-default-rtdb.asia-southeast1.firebasedatabase.app")


    /**
     * 화면 설정
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).
        inflate(R.layout.user_layout, parent, false)

        return UserViewHolder(view)
    }

    /**
     * 데이터 설정
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        //데이터 담기
        val currentUser = userList[position]

        holder.nameText.setOnClickListener {
            val intent  = Intent(context, ChatActivity::class.java)

            //넘길 데이터
            intent.putExtra("nick", currentUser.user_nick)
            intent.putExtra("uId", currentUser.user_idx)
            intent.putExtra("listUid", currentUser.listUid)

            context.startActivity(intent)
        }
        //화면에 데이터 보여주기
        holder.nameText.text = currentUser.user_nick
        holder.listIdx.text = currentUser.listUid

        //아이템 클릭 이벤트
        holder.itemView.setOnClickListener {


            val intent  = Intent(context, ChatActivity::class.java)

            //넘길 데이터
            intent.putExtra("nick", currentUser.user_nick)
            intent.putExtra("uId", currentUser.user_idx)
            intent.putExtra("listUid", currentUser.listUid)

            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(currentUser.user_nick)
            builder.setMessage("채팅방을 나가시겠습니까?")

// 확인 버튼 추가
            builder.setPositiveButton("채팅방 나가기") { dialog, which ->
                receiverRoom = MySharedpreferences.getUserIdx(context) + currentUser.user_idx
                senderRoom =  currentUser.user_idx +MySharedpreferences.getUserIdx(context)

                RetrofitBuilder.api.DeleteChatList(MySharedpreferences.getUserIdx(context),currentUser.user_idx, currentUser.listUid).enqueue(object:
                    Callback<Void> {
                    override fun onResponse(call: retrofit2.Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {


                            Toast.makeText(context, "채팅방을 나갔습니다", Toast.LENGTH_SHORT).show()

                            val deRef =
                                database.getReference("first").child("second").child(receiverRoom)
                            val deRef1 =
                                database.getReference("first").child("second").child(senderRoom)
                            deRef.removeValue()
                            deRef1.removeValue()



                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }

                })


            }

// 취소 버튼 추가
            builder.setNegativeButton("취소") { dialog, which ->
                // 취소 버튼을 클릭했을 때 수행할 동작
                // 여기에 코드를 작성하세요
            }

// 다이얼로그 표시
            val dialog = builder.create()
            dialog.show()
            true

        }


        true
    }




    /**
     * 데이터 갯수 가져오기
     */
    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText: TextView = itemView.findViewById(R.id.name_text)
        val listIdx: TextView = itemView.findViewById(R.id.list_id)
    }
}