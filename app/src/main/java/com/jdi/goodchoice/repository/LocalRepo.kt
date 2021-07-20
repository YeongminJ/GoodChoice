package com.jdi.goodchoice.repository

import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.model.Constant
import com.jdi.goodchoice.repository.db.FavoriteHotel
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

class LocalRepo {

    private fun openDB(): Realm {
        val config =
            RealmConfiguration.Builder().name(Constant.DB_NAME).allowQueriesOnUiThread(true)
                .schemaVersion(Constant.DB_VERSION)
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true).build()
        return Realm.getInstance(config)
    }

    fun getFavoriteList(): List<FavoriteHotel>? {
        var result: List<FavoriteHotel>? = null
        openDB().apply {
            where(FavoriteHotel::class.java).findAll()?.let {
                result = copyFromRealm(it)
            }
            close()
        }
        return result
    }

    fun getFavorite(hotelId: Int): FavoriteHotel? {
        var result: FavoriteHotel? = null
        openDB().apply {
            where(FavoriteHotel::class.java).equalTo(FavoriteHotel.FIELD_ID, hotelId).findFirst()?.let {
                result = copyFromRealm(it)
            }
            close()
        }
        return result
    }

    fun insertFavorite(hotel: Hotel) {
        if (!isFavorite(hotel)) {
            openDB().apply {
                beginTransaction()
                val fHotel = FavoriteHotel()
                fHotel.id = hotel.id
                fHotel.date = Date()
                copyToRealm(fHotel)
                commitTransaction()
                close()
            }
        }
    }

    fun removeFavorite(hotel: Hotel) {
        openDB().apply {
            beginTransaction()
            val fHotel = where(FavoriteHotel::class.java).equalTo(FavoriteHotel.FIELD_ID, hotel.id).findFirst()
            fHotel?.deleteFromRealm()
            commitTransaction()
            close()
        }
    }

    fun isFavorite(hotel: Hotel): Boolean {
        var result = false
        openDB().apply {
            where(FavoriteHotel::class.java).equalTo(FavoriteHotel.FIELD_ID, hotel.id).findFirst()?.apply {
                result = true
            }
            close()
        }
        return result
    }
}