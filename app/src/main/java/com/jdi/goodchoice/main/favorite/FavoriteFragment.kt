package com.jdi.goodchoice.main.favorite

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
import androidx.fragment.app.Fragment
import com.jdi.goodchoice.R
import com.jdi.goodchoice.api.Hotel
import com.jdi.goodchoice.databinding.FavoriteListBinding
import com.jdi.goodchoice.detail.DetailActivity
import com.jdi.goodchoice.main.FavoriteAdapter
import com.jdi.goodchoice.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment: Fragment() {

    lateinit var binding: FavoriteListBinding
    val viewModel: MainViewModel by sharedViewModel()
    lateinit var startForResult: ActivityResultLauncher<Intent>

    var listAdapter = FavoriteAdapter()

    private val ORDER_BY_DATE = 0
    private val ORDER_BY_RATE = 1

    var crrOrder = ORDER_BY_DATE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.d("JDI", "onResult $result")
            if (result.resultCode == Activity.RESULT_OK) {
                val hotel =
                    result.data?.getSerializableExtra(DetailActivity.EXTRA_HOTEL) as Hotel
                viewModel.updateFavorite(hotel)
                listAdapter.notifyDataSetChanged()
            }
        }
        binding = FavoriteListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@FavoriteFragment
            this.vm = viewModel
            recyclerView.adapter = listAdapter.apply {
                onItemClick = {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_HOTEL, it)
                        startForResult.launch(this)
                    }
                }
            }
            btnSort.setOnClickListener {
                when(crrOrder) {
                    ORDER_BY_DATE-> {
                        crrOrder = ORDER_BY_RATE
                        listAdapter.submitList(viewModel.favoriteRateHotel.value)
                        btnSort.text = getString(R.string.order_rate)
                    }
                    else -> {
                        crrOrder = ORDER_BY_DATE
                        listAdapter.submitList(viewModel.favoriteDateHotel.value)
                        btnSort.text = getString(R.string.order_register)
                    }
                }
            }
        }
        with(viewModel) {
            favoriteDateHotel.observe(viewLifecycleOwner, {
                Log.d("JDI", "hotelList Observed ${it.size}")
                if (crrOrder == ORDER_BY_DATE) {
                    listAdapter.submitList(it)
                }
            })
            favoriteRateHotel.observe(viewLifecycleOwner, {
                Log.d("JDI", "hotelList Observed ${it.size}")
                if (crrOrder == ORDER_BY_RATE) {
                    listAdapter.submitList(it)
                }
            })
        }

        return binding.root
    }
}