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
        Log.d("JDI", "setScrollEndListener $scrollEvent")
        recycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                Log.d("JDI", "onScrollStateChanged $newState")
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
        Log.d("JDI", "setImage : $url")
        url?.let {
            Glide.with(view.context).load(it).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("favorite")
    fun adaptFavorite(view: ImageView, hotel: Hotel) {

    }
}