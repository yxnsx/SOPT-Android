package com.yxnsx.sopt.util

import android.view.View
import com.yxnsx.sopt.week02.reqres_api.ReqresListUsersModel


interface ItemListeners {
    fun onClickReqresListUsersItem(view: View, userData: ReqresListUsersModel.Data)
}