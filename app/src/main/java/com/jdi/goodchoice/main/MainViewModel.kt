package com.jdi.goodchoice.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.repository.NetworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repo: NetworkRepo) : ViewModel() {

    private var _hotels = MutableLiveData<List<Hotel>>()
    var hotels: LiveData<List<Hotel>> = _hotels

    var index: Int = 1

    init {
        getNextHotels()
    }

    fun getNextHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getHotels(index)

            _hotels.postValue(result)
            index++
        }
    }
}