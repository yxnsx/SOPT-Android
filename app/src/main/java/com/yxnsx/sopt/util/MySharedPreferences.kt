package com.yxnsx.sopt.util

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Nullable

class MySharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    fun getString(key: String, @Nullable defValue: String): String {
        return sharedPreferences.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }


    companion object {
        const val NAME = "SharedPreferences"
        const val MODE = Context.MODE_PRIVATE
    }

}