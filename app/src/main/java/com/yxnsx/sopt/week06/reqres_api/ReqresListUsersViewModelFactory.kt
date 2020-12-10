package com.yxnsx.sopt.week06.reqres_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class ReqresListUsersViewModelFactory(
    private val repository: ReqresListUsersRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReqresListUsersViewModel(
            repository
        ) as T
    }
}