package com.yxnsx.sopt.week06.sopt_api

import com.yxnsx.sopt.util.BASE_URL_SOPT_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object SoptRetrofitObject {

    // SingleTon
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SOPT_USER_API : SoptUserApi = retrofit.create(
        SoptUserApi::class.java)
}