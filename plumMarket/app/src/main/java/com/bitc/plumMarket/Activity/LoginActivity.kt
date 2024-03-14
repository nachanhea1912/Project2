package com.bitc.plumMarket.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bitc.plumMarket.Data.LoginData
import com.bitc.plumMarket.MySharedpreferences
import com.bitc.plumMarket.RetrofitBuilder
import com.bitc.plumMarket.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            val id =  binding.edId.text.toString()
            val pw = binding.edPass.text.toString()

            RetrofitBuilder.api.getPostList(id,pw).enqueue(object: Callback<LoginData>{
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                    if(response.isSuccessful){
                        val address =  response.body()?.user_address.toString()
                        val nick =  response.body()?.user_nick.toString()
                        val email =  response.body()?.user_email.toString()
                        val idx = response.body()?.user_idx.toString()
                        val id = response.body()?.user_id.toString()
                        val fileUrl = response.body()?.user_profile.toString()
                        MySharedpreferences.setFileUrl(this@LoginActivity,fileUrl)
                        MySharedpreferences.setUserAddress(this@LoginActivity, address)
                        MySharedpreferences.setUserNick(this@LoginActivity, nick)
                        MySharedpreferences.setUserEmail(this@LoginActivity, email)
                        MySharedpreferences.setUserIdx(this@LoginActivity, idx)
                        MySharedpreferences.setUserId(this@LoginActivity, id)

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                }
            })
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}