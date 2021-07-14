package com.jdi.goodchoice.api

data class ResponseData(
    val msg: String,
    val data: HotelsInfo,
    val code: Int
)

data class HotelsInfo(
    val totalCount: Int,
    val product: List<Hotel>
)

data class Hotel(
    val id: Int,
    val name: String,
    val thumbNail: String,
    val description: HotelDescription,
    val rate: Float
)

data class HotelDescription(
    val imagePath: String,
    val subject: String,
    val price: Long
)