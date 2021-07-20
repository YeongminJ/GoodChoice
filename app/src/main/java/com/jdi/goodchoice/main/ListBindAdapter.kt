package com.jdi.goodchoice.main

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jdi.goodchoice.api.Hotel

object ListBindAdapter {

    @JvmStatic
    @BindingAdapter("scrollEndListener")
    fun setScrollEndListener(recycler: RecyclerView, scrollEvent: (() -> (Unit))?) {
        recycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    scrollEvent?.invoke()
                }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context).load(it).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("favorite")
    fun adaptFavorite(view: ImageView, hotel: Hotel) {
        if (hotel.isFavorite) {
            view.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            view.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }
}