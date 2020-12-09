package com.yxnsx.sopt.week06.kakao_search_api

import com.yxnsx.sopt.util.BASE_URL_KAKAO_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object KakaoRetrofitClient {
    // SingleTon
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_KAKAO_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SOPT_USER_DATA_SERVICE : KakaoSearchService = retrofit.create(
        KakaoSearchService::class.java)
}