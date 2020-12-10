package com.yxnsx.sopt.week06.kakao_search_api

import com.yxnsx.sopt.util.BASE_URL_KAKAO_API
import com.yxnsx.sopt.util.KAKAO_SEARCH_API_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface KakaoSearchApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: KakaoAK $KAKAO_SEARCH_API_KEY"
    )
    @GET("/v2/search/web")
    suspend fun getSearchResult(
        @Query("query") query: String
    ): Response<KakaoSearchModel>


    companion object {
        operator fun invoke(): KakaoSearchApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_KAKAO_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoSearchApi::class.java)
        }
    }
}