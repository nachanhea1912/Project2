package com.bitc.plumMarket.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.R
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class SlideAdapter (private val context: Context,private val imageUriList: List<String>):RecyclerView.Adapter<SlideAdapter.PagerViewHolder>(){

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.slide, parent, false)) {
        val slideImage = itemView.findViewById<ImageView>(R.id.slideImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))
    override fun getItemCount(): Int = imageUriList.size


    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference("image")
        val fileName = imageUriList[position]

        if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
            // 조건이 충족되는 경우의 처리 로직
            val pathReference = storageReference.child(fileName)
            pathReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context).load(uri).into(holder.slideImage);
            }.addOnFailureListener {
                Log.d("image", "가져오기 실패")
            }

        } else {

            Log.d("image", "데이터 없음")
        }
    }
}