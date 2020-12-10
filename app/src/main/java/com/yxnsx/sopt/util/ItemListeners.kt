package com.yxnsx.sopt.util

import android.view.View
import com.yxnsx.sopt.week06.reqres_api.ReqresListUsersModel


interface ItemListeners {
    fun onClickReqresListUsersItem(view: View, userData: ReqresListUsersModel.Data)
}