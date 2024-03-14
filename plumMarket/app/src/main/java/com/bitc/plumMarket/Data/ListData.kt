package com.bitc.plumMarket.Data

data class ListData(

    val list_idx: Int,
    val list_title: String,
    val list_content: String,
    val list_user_nick: String,
    val list_create_date: String,
    val list_completed_yn: String,
    val list_cate: String,
    val list_loc: String,
    val list_money: Int,
    val list_image_name :String,
    val list_sell_state:String,
    val list_hit_cnt: String,
    val user_profile:String,
    val user_idx: String

){
    constructor(idx:Int,title: String, money: Int, image:String) : this(
        list_idx = idx,
        list_title = title,
        list_content = "",
        list_user_nick = "",
        list_create_date = "",
        list_completed_yn = "",
        list_cate = "",
        list_loc = "",
        list_money = money,
        list_image_name = image,
        list_sell_state = "",
        list_hit_cnt = "",
        user_profile = "",
        user_idx = ""
    )
}

