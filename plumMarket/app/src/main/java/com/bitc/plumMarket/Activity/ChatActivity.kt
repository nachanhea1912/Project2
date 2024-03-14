package com.bitc.plumMarket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bitc.plumMarket.Data.Message
import com.bitc.plumMarket.Adapter.MessageAdapter
import com.bitc.plumMarket.Adapter.SlideAdapter
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityChatBinding
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {
    private lateinit var messageList: ArrayList<Message>
    val database = Firebase.database("https://androidchat-c4b8a-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef = database.getReference("first")
    private lateinit var receiverName: String
    private lateinit var receiverUid: String
    private lateinit var listUid: String
    val storage = FirebaseStorage.getInstance()
    val storageReference = storage.getReference("image")

    private lateinit var receiverRoom: String //받는 대화방
    private lateinit var senderRoom: String //보낸 대화방

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)	//툴바 사용 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)	//왼쪽 버튼 사용설정(기본은 뒤로가기)

        supportActionBar!!.setDisplayShowTitleEnabled(true)		//타이틀 보이게 설정


        messageList = ArrayList()
        val messageAdapter: MessageAdapter = MessageAdapter(this, messageList)

        //RecyclerView
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = messageAdapter

        //보내는 유저
        val senderUid = MySharedpreferences.getUserIdx(this)

        //넘어온 데이터 변수에 담기
        receiverName = intent.getStringExtra("nick").toString()
        receiverUid = intent.getStringExtra("uId").toString()
        listUid = intent.getStringExtra("listUid").toString()

        //보낸이방
        senderRoom = receiverUid + senderUid +"방"+ listUid

        //받는이방
        receiverRoom = senderUid + receiverUid +"방"+ listUid


        //액션바에 상대방 이름 보여주기
        supportActionBar?.title = receiverName


        var fileName :String = ""
        var state1 : String  = ""
        var nickChk :String = ""





//    뷰 페이저2에 사용할 프래그먼트 리스트 생성


        //    뷰 페이저2 어뎁터에 사용할 프래그먼트 리스트 등록

        //화면 상단
        RetrofitBuilder.api.DetailSelect(listUid).enqueue(object : Callback<List<ListData>> {
            override fun onResponse(call: Call<List<ListData>>, response: Response<List<ListData>>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    val items = mutableListOf<ListData>()

                    if (list != null) {
                        // listData를 활용하여 필요한 처리를 수행해주세요
                        // 예시: listData를 순회하며 각 객체의 필드를 읽어옴
                        for (data in list) {

                            val title = data.list_title
                            val money = data.list_money
                            val loc = data.list_loc
                            val content = data.list_content

                            val image = data.list_image_name
                            val viewCount = data.list_hit_cnt
                            val state = data.list_sell_state






                            // 이미지 변수를 imageUriList에 추가
                            fileName = image.toString()
                            binding.tvTitle1.text = title
                            state1 = state.toString()

                            nickChk = data.list_user_nick

                        }
                        if(state1.equals("0")){
                            binding.listState.text = "판매중"
                        }else if(state1.equals("1")){
                            binding.listState.text = "거래완료"
                            binding.btnState.isEnabled = false;
                        }else if(state1.equals("3")){
                            binding.listState.text = "예약중"
                        }
                        if(nickChk == MySharedpreferences.getUserNick(this@ChatActivity)){
                            binding.btnState.text = "거래 완료 하기"
                            binding.btnState.setOnClickListener {
                                RetrofitBuilder.api.UpdateBuy(receiverName,listUid).enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if(response.isSuccessful){
                                            Toast.makeText(this@ChatActivity,"거래완료되었습니다",Toast.LENGTH_SHORT).show()
                                            binding.btnState.isEnabled = false
                                        }
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Log.d("error", t.localizedMessage)
                                    }
                                })

                            }
                        }


                        if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
                            // 조건이 충족되는 경우의 처리 로직
                            val pathReference = storageReference.child(fileName)
                            pathReference.downloadUrl.addOnSuccessListener { uri ->
                                Glide.with(this@ChatActivity).load(uri).into(binding.listImage);
                            }.addOnFailureListener {
                                Log.d("image", "가져오기 실패")
                            }

                        } else {

                            Log.d("image", "데이터 없음")
                        }


                    } else {
                        Log.d("ysh", "listData is null")
                    }
                } else {
                    Log.d("ysh", "Server response unsuccessful. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ListData>>, t: Throwable) {
                Log.e("error", "Network request failed", t)
            }
        })





        var isFirstButtonClick = true

        binding.sendBtn.setOnClickListener {
            // 처음 버튼이 눌린 경우에만 실행
            if (isFirstButtonClick) {
                RetrofitBuilder.api.ChatEnter(senderUid,receiverUid,listUid).enqueue(object: Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("chat","성공")

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }
                })

                isFirstButtonClick = false
            }

            // 계속해서 실행하고자 하는 코드
            val message = binding.messageEdit.text.toString()
            val messageObject = Message(message, senderUid)

            myRef.child("second").child(senderRoom).child("third").push()
                .setValue(messageObject).addOnSuccessListener {
                    myRef.child("second").child(receiverRoom).child("third").push()
                        .setValue(messageObject)
                }
        }


        //메시지 가져오기
        myRef.child("second").child(senderRoom).child("third")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()

                    for(postSnapshat in snapshot.children){

                        val message = postSnapshat.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    //적용
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainaction, menu)		//작성한 메뉴파일 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId){
            android.R.id.home->{	//각 버튼 마다 스낵바 메세지로 기능 구현
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_search->{

            }

        }

        return super.onOptionsItemSelected(item)
    }
}