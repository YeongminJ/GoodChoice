package com.jdi.goodchoice.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdi.goodchoice.BR
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.databinding.ListItemBinding

class FavoriteAdapter : ListAdapter<Hotel, FavoriteAdapter.ItemViewHolder>(diffUtil) {

    var toggleFavorite : ((Hotel)->Unit)? = null
    var onItemClick : ((Hotel)->Unit)? = null

    inner class ItemViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.itemDate.visibility = View.VISIBLE
            binding.itemBtnFavorite.visibility = View.GONE
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
        fun bind(hotel: Hotel) {
            binding.setVariable(BR.hotel, hotel)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Hotel>() {
            override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel) = oldItem.id == newItem.id
            override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel) = oldItem.toString() == newItem.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}