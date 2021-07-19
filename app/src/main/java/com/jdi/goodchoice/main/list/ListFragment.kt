package com.jdi.goodchoice.main.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import com.jdi.goodchoice.databinding.FragmentListBinding
import com.jdi.goodchoice.detail.DetailActivity
import com.jdi.goodchoice.main.HotelListAdapter
import com.jdi.goodchoice.main.MainViewModel
import com.jdi.goodchoice.model.Constant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
    var listAdapter = HotelListAdapter()

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("JDI", "onCreateView")
        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListFragment
            this.vm = viewModel
            recyclerView.adapter = listAdapter.apply {
                onItemClick = {
                    //go to detail
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_HOTEL, it)
                        startActivity(this)
                    }
                }
                toggleFavorite = {
                    //toggle favorite
                }
            }
        }
        with(viewModel) {
            hotels.observe(viewLifecycleOwner, {
                listAdapter.submitList(it)
            })
        }
        return binding.root
    }
}