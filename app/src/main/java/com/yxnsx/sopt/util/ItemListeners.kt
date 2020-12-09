package com.yxnsx.sopt.util

import android.view.View
import com.yxnsx.sopt.week02.UserData


interface ProfileItemClickListener {
    fun onClickProfileItem(view: View, userData: UserData)
}