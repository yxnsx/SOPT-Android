package com.yxnsx.sopt.week06.kakao_search_api

import retrofit2.Response
import java.io.IOException


abstract class KakaoSearchRequest {

    suspend fun <T : Any> requestApi(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.code().toString()
            )
        }
    }
}

class ApiException(message: String) : IOException(message)