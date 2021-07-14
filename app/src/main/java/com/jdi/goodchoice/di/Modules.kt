package com.jdi.goodchoice.di

import com.jdi.goodchoice.main.MainViewModel
import com.jdi.goodchoice.repository.NetworkRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        NetworkRepo()
    }

    viewModel {
        MainViewModel(get())
    }
}