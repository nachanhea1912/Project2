package com.bitc.plumMarket.Activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitc.plumMarket.Adapter.UserAdapter
import com.bitc.plumMarket.Data.ChatList
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityProfilesojoungBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.text.SimpleDateFormat
import java.util.Date


class profilesojoungActivity : AppCompatActivity() {



    lateinit var binding: ActivityProfilesojoungBinding
//    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilesojoungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //          뒤로 가기 버튼
        binding.btnBack.setOnClickListener {
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

//      완료 버튼 클릭스 프로필 페이지로 이동 버튼
        binding.btnMypageProfile.setOnClickListener {

            val uri:Uri = Uri.parse(MySharedpreferences.getFileUrl(this))

            imageUpload(uri)

            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            try {
                result.data?.data?.let { fileUri ->
                    val calRatio = calculateInSampleSize(
                        fileUri,
                        resources.getDimensionPixelSize(R.dimen.imgSize),
                        resources.getDimensionPixelSize(R.dimen.imgSize),
                    )

                    val options = BitmapFactory.Options()
                    options.inSampleSize = calRatio

                    var inputStream = contentResolver.openInputStream(fileUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream?.close()

                    bitmap?.let {
                        binding.profilePicture.setImageBitmap(bitmap)
                        MySharedpreferences.setFileUrl(this,fileUri.toString())

                    } ?: Log.d("nch-provider", "이미지 없음")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.btnPicher.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val (height: Int, width: Int) = options.run {
            outHeight to outWidth
        }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize

    }

    private fun imageUpload(uri: Uri) {
        // storage 인스턴스 생성
        val storage = FirebaseStorage.getInstance()
        // storage 참조
        val storageRef = storage.getReference("image")
        val uid = MySharedpreferences.getUserId(this)
        // storage에 저장할 파일명 선언
        val fileName = SimpleDateFormat("yyyyMMddHHmm").format(Date())
        val mountainsRef = storageRef.child("profile${uid}${fileName}.png")


        val uploadTask = mountainsRef.putFile(uri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // 파일 업로드 성공
            Toast.makeText(this@profilesojoungActivity, "프로필 업로드 성공", Toast.LENGTH_SHORT).show();
            MySharedpreferences.setFileUrl(this,"profile${uid}${fileName}.png")

            val id = MySharedpreferences.getUserId(this)
            val uploadName = "profile${uid}${fileName}.png"

            RetrofitBuilder.api.UploadProfile(uploadName,id).enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {


                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })




        }.addOnFailureListener {
            // 파일 업로드 실패
            Toast.makeText(this@profilesojoungActivity, "프로필 업로드 실패", Toast.LENGTH_SHORT).show();
        }
    }

}
