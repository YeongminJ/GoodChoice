package com.jdi.goodchoice.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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

    override fun onBackPressed() {
        Intent().apply {
            putExtra(EXTRA_HOTEL, binding.hotel)
            setResult(RESULT_OK, this)
        }
        super.onBackPressed()
    }

    fun onFavariteClicked(view: View) {
        binding.hotel?.let {
            it.isFavorite = !it.isFavorite
            Intent().apply {
                putExtra(EXTRA_HOTEL, binding.hotel)
                setResult(RESULT_OK, this)
            }
            if (it.isFavorite) {
                (view as ImageView).setImageResource(android.R.drawable.star_big_on)
            }
            else {
                (view as ImageView).setImageResource(android.R.drawable.star_big_off)
            }
        }
    }
}