package com.yxnsx.week01

import android.app.Application


public class MyApplication : Application() {
    companion object {
        lateinit var mySharedPreferences: MySharedPreferences
    }

    override fun onCreate() {
        mySharedPreferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}
