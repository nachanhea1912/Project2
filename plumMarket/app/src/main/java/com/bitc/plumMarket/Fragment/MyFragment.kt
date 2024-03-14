package com.bitc.plumMarket.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitc.plumMarket.Activity.GansimActivity
import com.bitc.plumMarket.Activity.GumaeActivity
import com.bitc.plumMarket.Activity.LoginActivity
import com.bitc.plumMarket.Activity.MainActivity
import com.bitc.plumMarket.Activity.PanmaeActivity
import com.bitc.plumMarket.Activity.ProfileActivity
import com.bitc.plumMarket.Data.LoginData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityMypageBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFragment : Fragment() {

    private lateinit var binding: ActivityMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = ActivityMypageBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
           val storage = FirebaseStorage.getInstance()
        val fileName = MySharedpreferences.getFileUrl(requireContext())
        val storageReference = storage.getReference("image")

        binding.username.text = MySharedpreferences.getUserNick(requireContext())
        binding.email.text = MySharedpreferences.getUserEmail(requireContext())

        Log.d("fileName", fileName)
        if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
            // 조건이 충족되는 경우의 처리 로직
            val pathReference = storageReference.child(fileName)
            pathReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(binding.profilePicture);
            }.addOnFailureListener {
                Log.d("image", "가져오기 실패")
            }

        } else {

            Log.d("image", "데이터 없음")
        }

        binding.btnGansim.setOnClickListener {
            val intent = Intent(requireContext(), GansimActivity::class.java)
            startActivity(intent)
        }

        binding.btnPanmae.setOnClickListener {
            val intent = Intent(requireContext(), PanmaeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGumae.setOnClickListener {
            val intent = Intent(requireContext(), GumaeActivity::class.java)
            startActivity(intent)
        }

        binding.profilePicture.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnMypageProfile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.logoutButton.setOnClickListener {
            MySharedpreferences.clearUser(requireContext())
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}