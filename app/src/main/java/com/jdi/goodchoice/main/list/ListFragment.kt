package com.jdi.goodchoice.main.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.databinding.FragmentListBinding
import com.jdi.goodchoice.detail.DetailActivity
import com.jdi.goodchoice.main.HotelListAdapter
import com.jdi.goodchoice.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    var listAdapter = HotelListAdapter()

    private val viewModel: MainViewModel by sharedViewModel()
    lateinit var startForResult: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startForResult = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            Log.d("JDI", "onResult $result")
            if (result.resultCode == Activity.RESULT_OK) {
                val hotel =
                    result.data?.getSerializableExtra(DetailActivity.EXTRA_HOTEL) as Hotel
                viewModel.updateFavorite(hotel)
                listAdapter.notifyDataSetChanged()
            }
        }
        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListFragment
            this.vm = viewModel
            recyclerView.adapter = listAdapter.apply {
                onItemClick = {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_HOTEL, it)
                        startForResult.launch(this)
                    }
                }
                toggleFavorite = {
                    //toggle favorite
                    viewModel.updateFavorite(it)
                }
            }
        }
        with(viewModel) {
            hotels.observe(viewLifecycleOwner, {
                Log.d("JDI", "hotelList Observed ${it.size}")
                listAdapter.submitList(it)
            })
        }
        return binding.root
    }


}