package com.bitc.plumMarket.Activity


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitc.plumMarket.Data.ListImageDTO
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.R
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityWriteBinding
import com.bitc.plumMarket.databinding.ItemImageBinding
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

class WriteActivity : AppCompatActivity() {
    private lateinit var uris: List<Uri>
    private lateinit var binding: ActivityWriteBinding
    private lateinit var imageAdapter: ImageAdapter
    private val maxImageCount = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // XML 레이아웃과 연결
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이미지 어댑터 초기화
        imageAdapter = ImageAdapter()

        // 리사이클러뷰에 레이아웃 매니저 및 어댑터 설정
        binding.imageRecyclerView.apply {
            layoutManager = GridLayoutManager(this@WriteActivity, 3)
            adapter = imageAdapter

            setSupportActionBar(binding.toolbar)	//툴바 사용 설정

            supportActionBar!!.setDisplayHomeAsUpEnabled(true)	//왼쪽 버튼 사용설정(기본은 뒤로가기)

            supportActionBar!!.setDisplayShowTitleEnabled(true)		//타이틀 보이게 설정
        }

        // 갤러리 열기를 위한 런처 등록
        val requestGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                try {
                    // 선택된 이미지 URI 리스트
                    uris = if (result.data?.clipData != null) {
                        (0 until result.data!!.clipData!!.itemCount).map {
                            result.data!!.clipData!!.getItemAt(it).uri
                        }
                    } else {
                        listOf(result.data?.data!!)
                    }

                    // 이미지 개수 제한 체크
                    if (uris.size + imageAdapter.itemCount > maxImageCount) {
                        // 이미지 선택 개수가 제한을 초과하는 경우
                        Toast.makeText(this@WriteActivity, "3장까지 선택 가능합니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        // 이미지 어댑터에 이미지 추가 및 리사이클러뷰 표시
                        imageAdapter.addImages(uris)
                        binding.imageRecyclerView.visibility = RecyclerView.VISIBLE
                        MySharedpreferences.setFileUrls(this@WriteActivity,uris)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        // 이미지 선택 버튼 클릭 시 갤러리 열기
        binding.imagechange.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            requestGalleryLauncher.launch(intent)
        }

//           데이터를 등록 가능하게 만들수 있는 코드 작성 부분
        binding.btnInsetrt.setOnClickListener {
            val title = binding.edTitle.text.toString()
            val money = binding.edMoney.text.toString()
            val content = binding.edcontent.text.toString()
            val loc = binding.btnMap.text.toString()
            val nick = MySharedpreferences.getUserNick(applicationContext);



            RetrofitBuilder.api.insertListData(title,money,content,loc,nick).enqueue(object : Callback<Int> {
            override fun onResponse(
                call: Call<Int>,
                response: Response<Int>
            ) {
                if (response.isSuccessful) {
                    var result = response.body()
                    MySharedpreferences.setListIdx(this@WriteActivity,result.toString())
                    Toast.makeText(this@WriteActivity,"등록되었습니다.",Toast.LENGTH_SHORT).show()

                } else {
                    Log.d("ysh", "listData is null")
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("error", t.localizedMessage)
            }


        })
            if(!uris.equals("null") or !uris.equals(null)) {
                imageUpload(uris)
            }else{
                Log.d("ysh","사진등록 x")
            }

            binding.btnMap.setOnClickListener {
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
        private var imageUris: MutableList<Uri> = mutableListOf()


        // 이미지 어댑터에 이미지 추가
        fun addImages(uris: List<Uri>) {
            imageUris.addAll(uris)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            // 뷰홀더 생성
            val binding =
                ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            // 뷰홀더와 데이터 바인딩
            holder.bind(imageUris[position])
        }
        override fun getItemCount(): Int = imageUris.size

        class ImageViewHolder(private val binding: ItemImageBinding) :
            RecyclerView.ViewHolder(binding.root) {
            // 이미지 뷰홀더에 이미지 설정
            fun bind(uri: Uri) {
                binding.imageViewItem.setImageURI(uri)
            }
        }
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

    private fun imageUpload(uri: List<Uri>) {

        val storage = FirebaseStorage.getInstance()
        // storage 참조
        val storageRef = storage.getReference("image")
        val list: MutableList<ListImageDTO> = mutableListOf()

        for(item in uri){

            // storage 인스턴스 생성

            val uid = MySharedpreferences.getUserId(this)
            // storage에 저장할 파일명 선언
            val fileName = SimpleDateFormat("yyyyMMddHHmm").format(Date())

            val index = uri.indexOf(item)

            val mountainsRef = storageRef.child("list${uid}${fileName}i${index}.png")
            val uploadTask  = mountainsRef.putFile(item)
            uploadTask.addOnSuccessListener { taskSnapshot ->
                // 파일 업로드 성공


                val idx = MySharedpreferences.getListIdx(this)
                val uploadName = "list${uid}${fileName}i${index}.png"


                val listImageDTO = ListImageDTO(idx, uploadName)
                list.add(listImageDTO)






                RetrofitBuilder.api.insertImage(list).enqueue(object: Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("list","성공")

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("error", t.localizedMessage)
                    }
                })





            }.addOnFailureListener {
                // 파일 업로드 실패
            }
        }




    }

}