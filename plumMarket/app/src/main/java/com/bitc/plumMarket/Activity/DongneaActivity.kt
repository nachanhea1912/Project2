package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.databinding.ActivityDongneaBinding


class DongneaActivity : AppCompatActivity() {

    private lateinit var seekBarDistance: SeekBar
    private lateinit var textViewDistance: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDongneaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seekBarDistance = binding.seekBarDistance
        textViewDistance = binding.textViewDistance

        // SeekBar 최대 값 설정
        seekBarDistance.max = 50

        // SeekBar 이벤트 처리
        seekBarDistance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 사용자가 거리를 선택할 때마다 TextView 업데이트 (최대 50km로 제한)
                val limitedProgress = progress.coerceAtMost(50)
                val distanceText = "거리: $limitedProgress km"
                textViewDistance.text = distanceText

                // SeekBar의 progress 값을 최대 50으로 제한
                seekBar?.progress = limitedProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 사용자가 터치를 시작할 때의 동작
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 사용자가 터치를 멈출 때의 동작
            }
        })

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
