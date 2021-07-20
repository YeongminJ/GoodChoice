package com.jdi.goodchoice

import android.app.Application
import com.jdi.goodchoice.di.appModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GoodChoiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)

        startKoin {
            androidContext(this@GoodChoiceApp)
            modules(appModule)
        }
    }

}