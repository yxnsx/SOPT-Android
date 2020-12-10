package com.yxnsx.sopt.week06.reqres_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yxnsx.sopt.util.Coroutines
import kotlinx.coroutines.Job


class ReqresListUsersViewModel(
    private val repository: ReqresListUsersRepository
) : ViewModel() {

    private lateinit var coroutineJob: Job
    private val reqresListUsersMutableLiveData = MutableLiveData<List<ReqresListUsersModel.Data>>()
    val reqresListUsersLiveData: LiveData<List<ReqresListUsersModel.Data>> get() = reqresListUsersMutableLiveData


    fun getReqresUsers() {
        coroutineJob = Coroutines.ioThenMain(
            { repository.getReqresUsers() },
            { reqresListUsersMutableLiveData.value = it?.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::coroutineJob.isInitialized) coroutineJob.cancel()
    }
}