package com.yxnsx.sopt.week06.sopt_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST


interface SoptUserApi {

    @Headers("Content-Type: application/json")
    @POST("/users/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<ResponseUserData>

    @POST("/users/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseUserData>
}