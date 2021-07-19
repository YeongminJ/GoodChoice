package com.jdi.goodchoice.main.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jdi.goodchoice.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment: Fragment() {

    val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("JDI", "ElseFragment")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}