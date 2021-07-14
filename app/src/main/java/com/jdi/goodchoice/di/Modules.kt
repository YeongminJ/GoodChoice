package com.jdi.goodchoice.di

import com.jdi.goodchoice.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel()
    }
}