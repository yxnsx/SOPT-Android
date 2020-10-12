package com.yxnsx.week01

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    private val NAME = "SharedPreferences"
    private val MODE = Context.MODE_PRIVATE
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    fun getString(key: String): String {
        return sharedPreferences.getString(key, null).toString()
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

}