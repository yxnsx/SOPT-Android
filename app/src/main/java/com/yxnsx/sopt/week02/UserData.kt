package com.yxnsx.sopt.week02

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var image: Int,
    var title: String,
    var subTitle: String,
    var date: String,
    var description: String
): Parcelable
