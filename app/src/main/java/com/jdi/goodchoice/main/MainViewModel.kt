package com.jdi.goodchoice.main

import android.util.Log
import androidx.lifecycle.*
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.repository.LocalRepo
import com.jdi.goodchoice.repository.NetworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(val repo: NetworkRepo, val localRepo: LocalRepo) : ViewModel() {

    private var _hotels = MutableLiveData<MutableList<Hotel>>(mutableListOf())
    var hotels: LiveData<List<Hotel>> = _hotels.map { it.toList() }
    var favoriteDateHotel: LiveData<List<Hotel>> = _hotels.map { it.toList().sortedBy { sort-> sort.date }.filter { hotel -> hotel.isFavorite } }
    var favoriteRateHotel: LiveData<List<Hotel>> = _hotels.map { it.toList().sortedByDescending { item-> item.rate }.filter { hotel -> hotel.isFavorite } }

    var index: Int = 1

    init {
        getNextHotels()
    }

    fun getNextHotels() {
        Log.d("JDI", "getNextHotels : $index")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.getHotels(index)
                for (i in result.indices) {
                    localRepo.getFavorite(result[i].id)?.let { fHotel->
                        result[i].isFavorite = true
                        result[i].date = fHotel.date
                    }
                }
                _hotels.apply {
                    value?.addAll(result)
                    postValue(this.value)
                }

                index++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateFavorite(hotel: Hotel) {
        if (localRepo.isFavorite(hotel)) {
            //remove
            localRepo.removeFavorite(hotel)
        } else {
            //insert
            localRepo.insertFavorite(hotel)
        }
        //update Hotel
        _hotels.value?.let {
            for (i in it.indices) {
                if (it[i].id == hotel.id) {
                    it[i] = hotel
                    break
                }
            }
        }
        //to Refresh Favorite List
        _hotels.apply {
            postValue(value)
        }
    }
}