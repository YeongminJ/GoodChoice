package com.jdi.goodchoice.api

import io.realm.RealmObject
import java.io.Serializable
import java.util.*

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
    val thumbnail: String,
    val description: HotelDescription,
    val rate: Float,
    var isFavorite: Boolean,
    var date: Date = Date()
): Serializable {
    override fun toString(): String {
        return "$id+$name$isFavorite"
    }
    companion object {
        val FIELD_ID = "id"
        val FIELD_DATE = "date"
    }
}

data class HotelDescription(
    val imagePath: String,
    val subject: String,
    val price: Long
): Serializable

