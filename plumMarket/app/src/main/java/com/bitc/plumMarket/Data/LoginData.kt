package com.bitc.plumMarket.Data

data class LoginData(
    val user_idx: Int,
    val user_id: String,
    val user_nick: String,
    val user_pw: String,
    val user_email: String,
    val user_address: String,
    val user_phone_num: String,
    val user_deleted_yn: String,
    val user_favlist: String,
    val user_rating: String,
    val send_idx : Int,
    val receive_idx : Int,
    val user_profile : String
)
