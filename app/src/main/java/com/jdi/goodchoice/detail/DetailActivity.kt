package com.jdi.goodchoice.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jdi.goodchoice.R
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    companion object {
        val EXTRA_HOTEL = "extra_hotel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail).apply {
            val hotel = intent.getSerializableExtra(EXTRA_HOTEL) as Hotel
            this.hotel = hotel
        }
    }
}