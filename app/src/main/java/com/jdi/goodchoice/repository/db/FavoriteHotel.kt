package com.jdi.goodchoice.repository.db

import io.realm.RealmObject
import java.util.*

open class FavoriteHotel : RealmObject() {
    companion object {
        val FIELD_ID = "id"
        val FIELD_DATE = "date"
    }

    var id: Int = 0
    var date: Date = Date()
}