package com.jdi.goodchoice.api

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HotelService {

    @GET("/App/json/{index}.json")
    suspend fun getHotels(@Path("index")index: Int): ResponseData

}