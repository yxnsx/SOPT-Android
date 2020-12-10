package com.yxnsx.sopt.week06.sopt_api

data class ResponseUserData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val userName: String,
        val email: String,
        val password: String
    )
}