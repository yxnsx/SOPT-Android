package com.yxnsx.sopt.week06.kakao_search_api

import com.yxnsx.sopt.R
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoSearchService {

    @Headers("Authorization: KakaoAK " + R.string.kakao_rest_api_key.toString())
    @GET("/v2/search/web")
    fun getSearchResult(
        @Query("query") query: String
    ): Call<ResponseKakaoSearch>
}