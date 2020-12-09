package com.yxnsx.sopt.util

import android.app.Application


class MyApplication : Application() {

    override fun onCreate() {
        mySharedPreferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }


    companion object {
        lateinit var mySharedPreferences: MySharedPreferences
    }
}
