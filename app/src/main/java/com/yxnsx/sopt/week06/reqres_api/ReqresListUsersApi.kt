package com.yxnsx.sopt.week06.reqres_api

import com.yxnsx.sopt.util.BASE_URL_REQRES_API
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


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