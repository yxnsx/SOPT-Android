package com.yxnsx.sopt.week06.reqres_api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ReqresListUsersModel(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val support: Support,
    val data: List<Data>
) {
    data class Support(
        val text: String,
        val url: String
    )

    @Parcelize
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String
    ) : Parcelable
}