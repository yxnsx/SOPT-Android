package com.yxnsx.sopt.week02.reqres_api

import com.yxnsx.sopt.util.BASE_URL_KAKAO_API
import com.yxnsx.sopt.util.BASE_URL_REQRES_API
import com.yxnsx.sopt.util.KAKAO_SEARCH_API_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ReqresListUsersApi {

    @Headers("Content-Type: application/json")
    @GET("/api/users?page=2")
    suspend fun getReqresListUsersData(
    ): Response<ReqresListUsersModel>


    companion object {
        operator fun invoke(): ReqresListUsersApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_REQRES_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReqresListUsersApi::class.java)
        }
    }
}