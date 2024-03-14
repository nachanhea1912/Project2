package com.bitc.plumMarket.Data

data class ChatList(
    val user_idx : String,
    val user_nick : String,
    val send_idx : String,
    val receive_idx : String,
    val listUid :String
)
{
    constructor(): this("","","","","")
}