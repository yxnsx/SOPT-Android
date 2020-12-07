package com.yxnsx.sopt.week06

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    // SingleTon
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://15.164.83.210:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userDataService : UserDataService = retrofit.create(UserDataService::class.java)
}