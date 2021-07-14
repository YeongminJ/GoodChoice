package com.jdi.goodchoice

import android.app.Application
import org.koin.core.context.startKoin

class GoodChoiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

        }
    }

}