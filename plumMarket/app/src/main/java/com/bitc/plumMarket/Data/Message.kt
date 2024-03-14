package com.bitc.plumMarket.Data

data class Message(
    var message: String?,
    var sendId: String?
)
{
    constructor(): this("","")
}
