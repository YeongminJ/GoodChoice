package com.jdi.goodchoice

import android.app.Application
import com.jdi.goodchoice.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GoodChoiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GoodChoiceApp)
            modules(appModule)
        }
    }

}