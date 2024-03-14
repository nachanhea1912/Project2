package com.bitc.plumMarket.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Data.ListData
import com.bitc.plumMarket.Data.ListData1
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.ViewHolder.GumaeViewHolder
import com.bitc.plumMarket.databinding.ListGumaeItemBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class GumaeAdapter(val items: MutableList<ListData1>, private val context:Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GumaeViewHolder(
            ListGumaeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bind = (holder as GumaeViewHolder).binding

        bind.tvListIdx.text = items[position].list_idx.toString()
        bind.tvListTitle.text = items[position].list_title
        bind.tvListMoney.text = items[position].list_money.toString() + "원"

        val fileName = items[position].list_image_name.toString()

        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference("image")

        if (fileName != null && fileName != "noImage" && fileName != "null" && fileName.isNotBlank()) {
            // 조건이 충족되는 경우의 처리 로직
            val pathReference = storageReference.child(fileName)
            pathReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context).load(uri).into(bind.tvListImage);
            }.addOnFailureListener {
                Log.d("image", "가져오기 실패")
            }

        } else {

            Log.d("image", "데이터 없음")
        }




    }

}