package com.bitc.plumMarket.Activity

import AlleramAdapter
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.viewpager2.widget.ViewPager2
import com.bitc.plumMarket.Fragment.alleramFragment1
import com.bitc.plumMarket.Fragment.alleramFragment2
import com.bitc.plumMarket.Receiver.MyReceiver

import com.bitc.plumMarket.databinding.ActivityAlleramBinding
import com.google.android.material.tabs.TabLayoutMediator

class AlleramActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlleramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlleramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStatusAlarm.setOnClickListener {
            notiAlarm()
        }
        initViewPager()
    }

    private fun initViewPager() {
        // ViewPager2 Adapter 셋팅
        var alleramAdapter = AlleramAdapter(this)
        alleramAdapter.addFragment(alleramFragment1())
        alleramAdapter.addFragment(alleramFragment2())

        // Adapter 연결
        binding.AlleamViewPager2.apply {
            adapter = alleramAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        // ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tlNavigationView2, binding.AlleamViewPager2) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "활동 알림"
                1 -> tab.text = "키워드 알림"
            }
        }.attach()

        // 뒤로가기 버튼 사용 마이페이지 이동
        binding.btnBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun notiAlarm() {
        // getSystemService(서비스) : 현재 안드로이드 시스템에서 동작하고 있는 서비스 중 지정한 서비스를 가져옴
        // getSystemService() 메소드를 사용하여 NotificationManager 타입의 객체 가져오기
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // NotificationCompat 타입의 객체를 저장할 변수 선언
        val builder: NotificationCompat.Builder

        // API 26부터 채널이 추가 되어 버전에 따라 사용 방식을 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            // 채널 객체 생성
            val channel = NotificationChannel(
                channelId,
                channelName,
                // 알림 등급 설정
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "My Channel One description"
            channel.setShowBadge(true)

            // 생성된 채널 정보를 통해서 채널 생성
            manager.createNotificationChannel(channel)

            // NotificationCompat 타입의 객체 생성
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        // 스테이터스창 알림 화면 설정
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("알림창 제목")
        builder.setContentText("알림창 내용")

        // 화면 전환을 위한 intent를 저장할 리스트 변수 생성
        var mainIntent = mutableListOf<Intent>()
        // 리스트에 화면을 전환할 intent를 추가함
        mainIntent.add(Intent(this, MainActivity::class.java))

        // PendingIntent를 통해서 안드로이드 시스템에 이벤트 요청 처리 등록
        // getActivities()를 통해서 지정한 앱의 화면으로 전환 요청
        var eventPendingIntent = PendingIntent.getActivity(
            this, 30, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_MUTABLE
        )

        // 알림에 setContentIntent()를 사용하여 PendingIntent 추가하고 지정한 이벤트가 동작하도록 함
        builder.setContentIntent(eventPendingIntent)

        // 알림에는 액션 버튼을 3개까지 추가할 수 있음
        // 알림에 액션을 추가
        builder.addAction(
            // 매개변수는 아이콘, 액션버튼 제목, PendingIntent 객체 순서
            NotificationCompat.Action.Builder(
                android.R.drawable.stat_notify_more,
                "화면 전환",
                eventPendingIntent
            ).build()
        )

        // 알림창에서 답장하기
        // 답장 시 사용할 키 설정, 리시버에서 해당 키로 데이터를 가져옴
        val receiverKeyName = "csy-receive"
        // RemoteInput을 통해서 알림창에서 응답할 수 있도록 객체 생성
        var remoteInput: RemoteInput = RemoteInput.Builder(receiverKeyName).run {
            setLabel("응답")
            build()
        }

        // RemoteInput 답장 내용을 받아볼 리시버 intent 생성
        val receiverIntent = Intent(this, MyReceiver::class.java)
        // RemoteInput도 PendingIntent를 사용하여 안드로이드 서비스에 등록해야함
        val receiverPendingIntent = PendingIntent.getBroadcast(this, 50, receiverIntent, PendingIntent.FLAG_MUTABLE)

        // RemoteInput도 알림의 이벤트 액션이므로 addAction()을 사용하여 Action으로 등록
        builder.addAction(
            NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_send, "응답", receiverPendingIntent
            ).addRemoteInput(remoteInput).build()
        )

        // NotificationManager를 사용하여 스테이터스창에 알림창 출력
        manager.notify(11, builder.build())
    }
}
