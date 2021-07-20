package com.jdi.goodchoice.repository

import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.api.HotelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepo {

    private val BASE_URL =  "https://www.gccompany.co.kr"

    private val retrofit: Retrofit
    private val service: HotelService
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(HotelService::class.java)
    }

    suspend fun getHotels(index: Int): List<Hotel> {
        return service.getResponse(index).data.product
    }
}