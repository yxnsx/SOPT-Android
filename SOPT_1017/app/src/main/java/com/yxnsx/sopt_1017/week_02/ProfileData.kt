package com.yxnsx.sopt_1017.week_02

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData(
    var image: Int,
    var title: String,
    var subTitle: String,
    var date: String,
    var description: String
): Parcelable
